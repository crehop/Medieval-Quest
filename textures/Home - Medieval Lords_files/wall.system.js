Enjin_Wall_System = function(element, url, params) {
	this.init(element, url, params);
};

Enjin_Wall_System.__instances = {};
Enjin_Wall_System.getInstance = function(element) {
	return element.data('wall');
};

Enjin_Wall_System.prototype = {
	element: null,
	lastEmbed: {url: null, title: null, description: null},
	removedLink: '',

	init: function(element, url, params) {
		var self = this;
		self.element = element;
		self.element.data('wall', this);
		self.url = url;
		
		var options = {
			user_id: 0,
			site_id: 0
		};
		$.extend(options, params);

		/*
		 * Initialize post area
		 */
		self.element.find('.post-panel textarea').autoResize({minHeight:10, maxHeight:640, extraSpace:16, animate: false});

		self.element.find('.post-panel .wall-textarea textarea, .post-panel .wall-textarea input').bind('focus', function(){
			$(document).bind('click', {wallObject: self}, self.closePostInput);
		});

		self.element.find('.wall-post-input-empty').mousedown(function(){
			$(this).closest('.wall-post-area').removeClass('empty').addClass('status');
			setTimeout(function(){self.element.find('.post-panel.status .wall-textarea textarea').focus()}, 150);
		});
		
		self.element.find('.wall-link-input-empty').mousedown(function() {
			$(this).hide().siblings('.wall-textarea').show();
			setTimeout(function(){self.element.find('.post-panel.link .wall-textarea input').focus()}, 150);
			self.element.find('.wall-post-area .wall-upload-or, .wall-post-area .wall-upload-image').hide();
		});

		self.element.find('.wall-post-area, .post-panel textarea').bind('click', function(event){
			event.stopPropagation();
		});

		/*
		 * Embed detection
		 */
		self.element.find('.post-panel .wall-textarea textarea').bindWithDelay('keyup', function(event){
			var me = this;
			self.fetchEmbed($(me).closest('.wall-post-area').find('.post-panel.embed'), $(me).val());
		}, 750);
		
		self.element.find('.post-panel.link .wall-textarea input').keypress(function(e){
			if(e.keyCode == 13) {
				$(this).closest('.wall-post-area').find('.wall-post-share input').click();
			}
		});

		self.element.find('.wall-post-share input').click(function(){
			if($(this).val() == 'Attach') {
				var linkinput = $(this).closest('.wall-post-area').find('.post-panel:visible .wall-textarea input');
				if(linkinput.val().length) {
					self.fetchEmbed(linkinput.closest('.wall-post-area'), linkinput.val());
					Enjin_Core.disableButton(this, 1.2);
				}
			} else {
				var postarea = $(this).closest('.wall-post-area');
				var postpanel = postarea.find('.post-panel:visible');
				var textinput = postpanel.find('.wall-textarea textarea');
				var message = textinput.val();
				if($.trim(message).length == 0) {
					if(!self.lastEmbed.url) return;
				}
				
				self.element.find('.wall-post-share').addClass('disabled').children('input').attr('disabled', 'disabled');
				self.element.find('.wall-post-input textarea').attr('disabled', 'disabled').css({ opacity: 0.5 });

                if(self.element.find('.post-access-sitewall').length > 0) // Site Wall
                    var post_access = self.element.find('.post-access-sitewall').attr('data-access');
                else if(self.element.find('.wall-post-access').length > 0) // Profile
                    var post_access = self.element.find('.wall-post-access').attr('data-access');

				var postData = {cmd: 'post', message: message, access: post_access};
				if(self.lastEmbed.url) postData.embed_url = self.lastEmbed.url;
				if(postpanel.find('.embed-title').text().length) postData.embed_title = postpanel.find('.embed-title').text();
				if(postpanel.find('.embed-description').text().length) postData.embed_description = postpanel.find('.embed-description').text();
				postData.embed_disable_thumbnail = postpanel.find('.embed-options input.embed-no-thumbnail').is(':checked');
				postData.embed_description = postpanel.find('.embed-options input.embed-no-description').is(':checked') ? '' : postData.embed_description;

				$.ajax({
					type: "POST",
					url: self.url,
					data: postData,
					dataType: "json",
					success: function(data){
						postarea.find('.wall-post-share').removeClass('disabled').children('input').removeAttr('disabled');
						postarea.find('.wall-post-input textarea').removeAttr('disabled').css({ opacity: null });

						if(data.error !== undefined) alert(data.error);
						else {
							var newpost = $(self.createWallPost(data.post_id, data.username, data.message, data.avatar, data.timestamp, data.posted, data.access, data.embed_url, data.embed_title, data.embed_description, data.embed_thumbnail, data.embed_html, data.edited, data.comments_disabled));
							newpost.hide();
							newpost.find('span.message').expander({slicePoint: 500, expandPrefix: '', expandText: 'Read more'});
							self.element.find('.wall-posts-empty').remove();
							self.element.find('.posts').prepend(newpost);
							newpost.fadeIn(600);
							self.updatePostClasses();
							self.closePostInput({data:{wallObject: self}});
						}
					},
					error: function (XMLHttpRequest, textStatus, errorThrown) {
					}
				});

				self.setPostType('status');
				self.lastEmbed.url = null;
				self.lastEmbed.title = null;
				self.lastEmbed.description = null;
			}
		});

		this.uploader = new AjaxUpload('.wall-upload-image', {
			  action: self.url,
			  name: 'upload',
			  data: {
				cmd : 'upload-image'
			  },
			  autoSubmit: true,
			  responseType: "json",
			  onSubmit: function(file , ext){
			  },
			  onComplete: function(file, response) {
				  if(response.error != undefined || !response.url.length)
					alert('There was a problem uploading the image, please try again.');

				  var link = response.url;
					if(link.length) {
						self.element.find('.wall-post-input textarea').val('');
						self.fetchEmbed(self.element.find('.wall-post-area'), link);
						Enjin_Core.disableButton(this, 1.2);
						//Enjin_Wall_System.setPostType('status');
					}
			  }
			});

		$(this.uploader._parentDialog).appendTo(self.element.find('.wall-upload-image'));
		this.uploader.setPosition(0, 0);
		this.uploader.setWidth(96);
		this.uploader.setHeight(30);

		self.element.find('.wall-post-access').click(function(){
			if($(this).attr('data-access') == 'everyone') $(this).attr('data-access', 'friends').html('Friends');
			else $(this).attr('data-access', 'everyone').html('Public');
			$(this).blur();
		});
		
		self.element.find('.post-access-link').bind('click', function(event){
			Enjin_Core.dropdownMenu([
				['Anyone can post (public)', '', false, function(e){ $(event.target).closest('.element_wall').data('wall').setWallPostAccess('everyone','Public wall'); }],
				['Only friends can post', '', false, function(e){ $(event.target).closest('.element_wall').data('wall').setWallPostAccess('friends','Friends only'); }],
				['No one can post', '', false, function(e){ $(event.target).closest('.element_wall').data('wall').setWallPostAccess('nobody','No one can post'); }]
			], this, 'post-access-menu', true, -14);
		});

        self.element.find('.post-access-sitewall').bind('click', function(event){
            var site_access_types = [
                ['Public', '', false, function(e){ self.setNewSitePostAccess('all','Everyone'); }],
                ['Registered Users', '', false, function(e){ self.setNewSitePostAccess('registered','Registered Users'); }]
            ];
            if(Enjin_Core.is_admin) {
                site_access_types.push(['Admins', '', false, function(e){ self.setNewSitePostAccess('admins','Admins'); }]);
            }
            Enjin_Core.dropdownMenu(site_access_types, this, 'post-access-sitewall-menu', true, -14);
        });

		self.element.find('.wall-post-area .wall-post-types a').click(function(){
			self.setPostType($(this).attr('class'));
		});

		self.element.find('div.wall-post div.post-content span.message').expander({slicePoint: 500, expandPrefix: '', expandText: 'Read more'});
		self.element.find('div.wall-post div.comments span.comment-message').expander({slicePoint: 300, expandPrefix: '', expandText: 'Read more'});
	},

	closePostInput: function(event) {
		var self = event.data.wallObject;

		if(self.element.find('.post-panel.status .wall-textarea textarea').val().length == 0) {
			if(!self.element.find('.post-panel.status').is(':hidden')) self.setPostType('empty');
			$(document).unbind('click', self.closePostInput);
		}

		if(self.element.find('.post-panel.link .wall-textarea input').val().length == 0) {
			if(!self.element.find('.post-panel.link').is(':hidden')) self.setPostType('empty');
			$(document).unbind('click', self.closePostInput);
			
			self.element.find('.post-panel.link .wall-textarea').hide();
			self.element.find('.post-panel.link .wall-link-input-empty').show();
		}
	},

	closeEmbedInput: function(event) {
		var self = event.data.wallObject;
		if(self.element.find('.post-panel.embed .wall-textarea textarea').val().length == 0) {
			self.element.find('.post-panel.embed .wall-textarea').hide();
			self.element.find('.post-panel.embed .wall-embed-input-empty').show();
			$(this).unbind('blur', self.closeEmbedInput);
		}
	},

	setPostType: function(type) {
		var self = this;
		self.element.find('.wall-post-area').removeClass('empty status link photo video embed');
		self.element.find('.wall-post-area').addClass(type);
		if(type == 'status') {
			self.element.find('.wall-post-share input').val('Share');
			self.element.find('.post-panel.status textarea').focus();
		}
		else if(type == 'empty' || type == 'embed') {
			self.element.find('.wall-post-share input').val('Share');
		}
		else {
			self.element.find('.wall-post-share input').val('Attach');
		}
		
		if(type != 'embed') {
			self.element.find('.post-panel.embed .embed').remove();
			self.lastEmbed.url = null;
			self.lastEmbed.title = null;
			self.lastEmbed.description = null;
		}

		if(type == 'photo') self.element.find('.wall-post-area .wall-upload-or, .wall-post-area .wall-upload-image').show();
		else self.element.find('.wall-post-area .wall-upload-or, .wall-post-area .wall-upload-image').hide();

		// Clear all previous inputs
		self.element.find('.wall-post-area .post-panel .wall-textarea input, .wall-post-area .post-panel.status .wall-textarea textarea').val('').change();
	},

	startComment: function(link, item_type) {
		var self = this;
		var post_id = $(link).closest('.wall-post').attr('data-post_id');
		var comments = $(link).closest('td.post').children('.comments');
		if(typeof item_type == undefined) var item_type = false;

		var html = "<div class='wall-new-comment cbox'><div class='input-text comment-input-empty'>Write a comment...</div><div class='comment-input'><div class='input-textarea'><textarea style='height: 30px;'></textarea></div><div class='element_button'><div class='l'></div><div class='r'></div><input type='button' class='post-comment-button' value='Post Comment'></div><div class='or-cancel'>or <a href=''>Cancel</a></div><div class='clearing'></div></div></div>";
		comments.append(html);
		comments.children('.triangle').show();
		$(link).parent().hide();

		var empty = $(comments).find('.comment-input-empty');
		empty.mousedown(function(e){
			var myself = this;
			$(myself).hide();
			$(myself).siblings('.comment-input').show();
			$(myself).siblings('.comment-input').find('textarea').blur(function(){
				if($(this).val().length == 0) {
					$(this).parent().parent().hide();
					$(this).parent().parent().siblings('.comment-input-empty').show();
				}
			});
			setTimeout(function(){ $(myself).siblings('.comment-input').find('textarea').focus(); }, 100);
		});
		$(comments).find('.comment-input textarea').autoResize({minHeight:10, maxHeight:640, extraSpace:16, animate: false});
		$(comments).find('.or-cancel a').mousedown(function(e){
			var post = $(this).closest('.wall-post');
			$(this).parent().siblings('.input-textarea').children('textarea').val('').blur();
			if(!$(this).closest('wall-post').hasClass('comments-disabled')) post.find('.comment-link .link').show();
			$(this).closest('.wall-new-comment').siblings('.triangle').hide();
			$(this).closest('.wall-new-comment').remove();
			return false;
		});

		var button = $(comments).find('.post-comment-button');
		button.click(function(){
			var textarea = $(this).parent().siblings('.input-textarea').children('textarea');
			var message = textarea.val();
			if($.trim(message).length == 0) return;

			$(this).parent().addClass('disabled').children('input').attr('disabled', 'disabled');
			textarea.attr('disabled', 'disabled').css({ opacity: 0.5 });

			$.ajax({
				type: "POST",
				url: self.url,
				data: {cmd: 'post-comment', post_id: post_id, message: message, item_type: item_type},
				dataType: "json",
				success: function(data){
					if(data.error !== undefined) alert(data.error);
					else {
						textarea.removeAttr('disabled').css({ opacity: null });
						textarea.val('').blur();
						var newcomment = $(self.createComment(data.comment_id, data.username, data.message, data.avatar, data.posted));
						newcomment.find('.comment-message').expander({slicePoint: 300, expandPrefix: '', expandText: 'Read more'});
						newcomment.hide();
						button.parent().removeClass('disabled').children('input').removeAttr('disabled');
						comments.find('.wall-new-comment').before(newcomment);
						newcomment.fadeIn(600);
						self.updateCommentClasses(comments);
					}
				},
				error: function (XMLHttpRequest, textStatus, errorThrown) {
				}
			});
		});

		comments.show();
		empty.mousedown();
		self.updateCommentClasses(comments);
	},

	createWallPost: function(post_id, username, message, avatar, timestamp, posted, access, embed_url, embed_title, embed_description, embed_thumbnail, embed_html, edited, comments_disabled) {
		var self = this;
		access_lock = '';
		if(access == 'everyone') access_lock = " &middot; <span class='access'>Public</span>";
		else if(access == 'friends') access_lock = " &middot; <span class='access'>Friends</span>";
		else if(access == 'registered') access_lock = " &middot; <span class='access'>Members only</span>";
		else if(access == 'admins') access_lock = " &middot; <span class='access'>Admins only</span>";

		if(comments_disabled) var comments_mode = ' comments-disabled';
		else comments_mode = '';

		var html = "<div class='wall-post" + comments_mode + "' data-post_id='" + post_id + "' data-timestamp='" + timestamp + "'>\
		<table>\
			<tr>\
				<td class='avatar'>" + avatar + "</td>\
				<td class='post'>\
					<div class='post-content'>" +
						username + "&nbsp;\
						<span class='message'>" + message + "</span>";
		if(embed_url != undefined && embed_url.length) {
			html += self.createEmbed(embed_url, embed_title, embed_description, embed_thumbnail, embed_html);
		}
		html += "		<div class='footer'>\
							<span class='posted'>" + posted + "</span><span class='comment-link'>";
								if(comments_disabled) html += "<span class='disabled-msg'> &middot; Comments Disabled</span>";
								else html += "<span class='link'> &middot; <a href='javascript:void(0);' onclick='$(this).closest(\".element_wall\").data(\"wall\").startComment(this);'>Comment</a></span>";
							html += "</span>" + access_lock + "\
						</div>\
						<a class='post-dropdown' href='javascript:void(0);' onclick='$(this).closest(\".element_wall\").data(\"wall\").postDropdown(this);'><div class='triangle'></div></a>\
					</div>\
					<div class='comments'>\
						<div class='triangle'></div>\
					</div>\
				</td>\
			</tr>\
		</table>\
	</div>";
		return html;
	},

	createEmbed: function(embed_url, embed_title, embed_description, embed_thumbnail, embed_html, editable) {
		html = '';
		var editable_html = '';
		var embed_options = '';

		if(embed_thumbnail != undefined && embed_thumbnail.length) var embed_with_thumbnail = ' with-thumbnail';
		else embed_with_thumbnail = '';

		if(embed_description == undefined) embed_description = '';

		/*
		 * Show extra elements if editable text was specified
		 */
		if(editable != undefined) {
			// show extra elements for editing/creating
			editable_html = "<div class='input-text wall-embed-input-empty'>Write something about this...</div>\
			<div class='input-textarea wall-textarea' style='display: none;'><textarea style='height: 30px;'></textarea></div>";
			embed_options = "<div class='embed-options'><label><input type='checkbox' class='embed-no-thumbnail'>No Thumbnail</label>&nbsp<label><input type='checkbox' class='embed-no-description'>No Description</label></div>";
			var embed_href = '';
		}
		else var embed_href = " href='" + embed_url + "' target='_blank' rel='nofollow'";

		/*
		 * Video embed
		 */
		if(embed_html != undefined && embed_html.length) {
			html += "<div class='embed" + embed_with_thumbnail + "'>" + editable_html +
				"<a class='embed-title'" + embed_href + ">" + embed_title + "</a>";
				if(embed_thumbnail != undefined && embed_thumbnail.length) {
					html += "<a class='embed-thumbnail-wrap' rel='nofollow' href='" + embed_url + "' onclick='$(this).closest(\".element_wall\").data(\"wall\").showEmbedHTML(this); return false;'>\
						<img class='embed-thumbnail' src='" + embed_thumbnail + "'>\
						<div class='play-icon'></div>\
					</a>";
				}
				html += "<div class='embed-description'>" + embed_description + "</div>" + embed_options +
			"</div>";
		}

		/*
		 * Image method
		 */
		else if(embed_thumbnail != undefined && embed_thumbnail.length && embed_thumbnail == embed_url) {
			html += "<div class='embed" + embed_with_thumbnail + "'>" + editable_html;
				if(embed_thumbnail != undefined && embed_thumbnail.length) {
					html += "<a class='embed-thumbnail-wrap' rel='nofollow' href='" + embed_url + "' target='_blank'>\
						<img class='embed-thumbnail' src='" + embed_thumbnail + "'>\
					</a>";
				}
			html += "</div>";
		}

		/*
		 * Standard method
		 */
		else {
			html += "<div class='embed" + embed_with_thumbnail + " standard'>" + editable_html;
				if(embed_thumbnail != undefined && embed_thumbnail.length) {
					html += "<a class='embed-thumbnail-small-wrap' rel='nofollow' href='" + embed_url + "' target='_blank'>\
						<div><img class='embed-thumbnail-small' src='" + embed_thumbnail + "'></div>\
					</a>";
				}
				html += "<a class='embed-title'" + embed_href + ">" + embed_title + "</a>\
				<div class='embed-description'>" + embed_description + "</div>" + embed_options;
			html += "</div>";
		}

		return html;
	},

	createComment: function(comment_id, username, message, avatar, posted) {
		var self = this;
		return "<div class='wall-comment cbox' data-comment_id='" + comment_id + "'>\
			<table>\
				<tr>\
					<td class='comment-avatar'>" + avatar + "</td>\
					<td class='comment'>\
						<div class='comment-content'>\
							" + username + "&nbsp;\
							<span class='comment-message'>" + message + "</span>\
							<div class='comment-footer'>\
								<span class='posted'>" + posted + "</span>\
								<span class='remove-comment'>&middot; <a class='remove-link' href='javascript:void(0);' onclick='$(this).closest(\".element_wall\").data(\"wall\").removeComment(this);'>Delete</a></span>\
							</div>\
						</div>\
					</td>\
				</tr>\
			</table>\
		</div>";
	},
	
	postDropdown: function(link, post_id) {
		var self = this;
		var post = $(link).closest('.wall-post');

		if(post.hasClass('site_created')) var type = 'site_created';
		else if(post.hasClass('user_joined')) var type = 'user_joined';
		else if(post.hasClass('user_record')) var type = 'user_record';
		else if(post.hasClass('views_record')) var type = 'views_record';
		else if(post.hasClass('trialpay_plan')) var type = 'trialpay_plan';
		else if(post.hasClass('donation')) var type = 'donation';
		else var type = 'wall_post';

		var items = [];

		if(type == 'wall_post') items = [['Delete post', '', false, function(e){ self.removePost(link); }]];

		if(post.hasClass('comments-disabled')) items.push(['Enable comments', '', false, function(e){ self.enableComments(link, type); }]);
		else items.push(['Disable comments', '', false, function(e){ self.disableComments(link, type); }]);

		if(post.find('.wall-comment').length) items.push(['Delete all comments', '', false, function(e) { self.deleteComments(link, type); }]);
		//items.push(['Edit post', '', false, function(e) { self.editPost(link); }]);

		Enjin_Core.dropdownMenu(items, $(link), 'post-dropdown-menu', true);
	},

	removePost: function(link) {
		var self = this;
		var cnf = confirm('Are you sure you want to remove this post?');
		if(cnf) {
			var post = $(link).closest('.wall-post');

			$.ajax({
				type: "POST",
				url: self.url,
				data: {cmd: 'remove-post', post_id: post.attr('data-post_id')},
				dataType: "json",
				success: function(data){
					if(data.error !== undefined) alert(data.error);
					else {}
				},
				error: function (XMLHttpRequest, textStatus, errorThrown) {
				}
			});

			post.fadeOut(300, function(){
				$(this).remove();
				self.updatePostClasses();
			});
		}
	},

	disableComments: function(link, status_type) {
		var self = this;
		var cnf = confirm('Are you sure you want to disable commenting on this post?');
		if(cnf) {
			var post = $(link).closest('.wall-post');

			$.ajax({
				type: "POST",
				url: self.url,
				data: {cmd: 'disable-comments', post_id: post.attr('data-post_id'), status_type: status_type},
				dataType: "json",
				success: function(data){
					if(data.error !== undefined) alert(data.error);
					else {}
				},
				error: function (XMLHttpRequest, textStatus, errorThrown) {
				}
			});

			post.addClass('comments-disabled');
			post.find('.comment-link .link').hide();
			post.find('.wall-new-comment').prev('.wall-comment').addClass('last');
			post.find('.wall-new-comment').remove();
			if(!post.find('.wall-comment').length) post.find('.comments').hide();
		}
	},

	enableComments: function(link, status_type) {
		var self = this;
		var cnf = confirm('Are you sure you want to enable commenting on this post?');
		if(cnf) {
			var post = $(link).closest('.wall-post');

			$.ajax({
				type: "POST",
				url: self.url,
				data: {cmd: 'enable-comments', post_id: post.attr('data-post_id'), status_type: status_type},
				dataType: "json",
				success: function(data){
					if(data.error !== undefined) alert(data.error);
					else {}
				},
				error: function (XMLHttpRequest, textStatus, errorThrown) {
				}
			});
			post.removeClass('comments-disabled');
			post.find('.comment-link .link').show();
			if(post.find('.wall-comment').length) post.find('.comments').show();
		}
	},

	deleteComments: function(link, status_type) {
		var self = this;
		var cnf = confirm('Are you sure you want to delete all the comments in this post?');
		if(cnf) {
			var post = $(link).closest('.wall-post');
			
			$.ajax({
				type: "POST",
				url: self.url,
				data: {cmd: 'remove-comments', post_id: post.attr('data-post_id'), status_type: status_type},
				dataType: "json",
				success: function(data){
					if(data.error !== undefined) alert(data.error);
					else {}
				},
				error: function (XMLHttpRequest, textStatus, errorThrown) {
				}
			});

			post.find('.cbox').remove();
			comments = post.find('.comments');
			comments.children('.triangle').hide();
			self.updateCommentClasses(comments);
			if(!post.hasClass('comments-disabled')) post.find('.comment-link .link').show();
		}
	},

	editPost: function(link) {

	},

	removeComment: function(link, item_type) {
		var self = this;
		var cnf = confirm('Are you sure you want to remove this comment?');
		if(cnf) {
			if(typeof item_type == undefined || !item_type) var item_type = false;

			var comment = $(link).closest('.wall-comment');
			var comments = comment.parent();

			var cmd = 'remove-comment';

			var data = {cmd: cmd, comment_id: comment.attr('data-comment_id')};
			if(comments.closest('.wall-post').hasClass('site_created')) data.status_type = 'site_created';
			else if(comments.closest('.wall-post').hasClass('user_joined')) data.status_type = 'user_joined';
			else if(comments.closest('.wall-post').hasClass('user_record')) data.status_type = 'user_record';
			else if(comments.closest('.wall-post').hasClass('views_record')) data.status_type = 'views_record';
			else if(comments.closest('.wall-post').hasClass('trialpay_plan')) data.status_type = 'trialpay_plan';
			else if(comments.closest('.wall-post').hasClass('donation')) data.status_type = 'donation';

			$.ajax({
				type: "POST",
				url: self.url,
				data: data,
				dataType: "json",
				success: function(data){
					if(data.error !== undefined) alert(data.error);
					else {}
				},
				error: function (XMLHttpRequest, textStatus, errorThrown) {
				}
			});

			comment.fadeOut(300, function(){
				$(this).remove();
				self.updateCommentClasses(comments);
				if(comments.children('.wall-comment').size() == 0 && comments.children('div').size() == 1) comments.children('.triangle').hide();
			});
		}
	},

	viewMorePosts: function(link) {
		var self = this;
		var posts = $(link).siblings('.posts');
		var lastpost = posts.children('.wall-post:last').attr('data-post_id');

		$(link).hide().blur();
		$(link).after("<div class='viewmore-loading'>Loading ...</div>");

		$.ajax({
			type: "POST",
			url: url_getposts,
			data: {mode: 'getposts', post: lastpost},
			dataType: "json",
			success: function(data){
				if(data.error !== undefined) {
                    //alert(data.error);
                    $(link).siblings('.viewmore-loading').remove();
                } else {
					var content = $(data.content);
					content.hide();
					content.find('span.message').expander({slicePoint: 500, expandPrefix: '', expandText: 'Read more'});
					content.find('span.comment-message').expander({slicePoint: 300, expandPrefix: '', expandText: 'Read more'});
					posts.append(content);
					content.fadeIn(600);
					$(link).siblings('.viewmore-loading').remove();
					$(link).show();
					if(data.num <= 10) $(link).remove();
				}
			},
			error: function (XMLHttpRequest, textStatus, errorThrown) {
			}
		});
	},

	updatePostClasses: function() {
		var self = this;
		self.element.find('.wall-post').removeClass('first');
		self.element.find('.wall-post:first').addClass('first');
	},

	updateCommentClasses: function(comments, stopViewAll) {
		var self = this;
		comments.children('.cbox').removeClass('first').removeClass('last');
		comments.children('.cbox:first').addClass('first');
		comments.children('.cbox:last').addClass('last');

		// Update "View all x comments" count
		var commentsCount = comments.find('.wall-comment').length;
		comments.find('.comments-view-all .count').text(commentsCount);
		if(!stopViewAll && commentsCount <= 3) self.viewAllComments(comments.find('.comments-view-all a')[0]);
	},

	viewAllComments: function(link) {
		var self = this;
		$(link).parent().siblings().show();
		comments = $(link).parent().parent();
		$(link).parent().remove();
		self.updateCommentClasses(comments, true);
	},

	setWallPostAccess: function(access, title) {
		var self = this;
		self.element.find('.post-access-link').text(title);
		$.ajax({
			type: "POST",
			url: self.url,
			data: {cmd: 'set-post-access', access: access},
			dataType: "json",
			success: function(data){
				if(data.error !== undefined) alert(data.error);
				else {
				}
			},
			error: function (XMLHttpRequest, textStatus, errorThrown) {
			}
		});
	},

    setNewSitePostAccess: function(access, title) {
        var self = this;
        self.element.find('.wall-post-access-box .post-access-sitewall').attr('data-access', access).text(title);
    },

	fetchEmbed: function(target, fetchmsg, force_title, force_description) {
		var self = this;
		$.post(self.url, {cmd: 'get-embed', msg: fetchmsg}, function(response) {
			if(response) {
				if(!response.url && response.thumbnail_url) response.url = response.thumbnail_url;
				if(response.url) {
					if(self.removedLink == response.url) return;

					if(force_title != undefined) response.title = force_title;
					if(force_description != undefined) response.description = force_description;

					if ( response.description.length > 180 ) { response.description = response.description.substr(0,180) + '...'; }

					self.lastEmbed.url = response.url;
					self.lastEmbed.title = response.title;
					self.lastEmbed.description = response.description;

					var embed_html = self.createEmbed(response.url, response.title, response.description, response.thumbnail_url, response.html, true);
					var container = self.element.find('.post-panel.embed .embed-container');
					container.html(embed_html);

					var status_text = target.parent().find('.post-panel.status .wall-textarea textarea').val();
					if(status_text.length) {
						container.find('.wall-embed-input-empty').hide();
						container.find('.wall-textarea').show();
						container.find('.wall-textarea textarea').val(status_text);
						setTimeout(function(){ container.find('.wall-textarea textarea').focus().setCursorPosition(status_text.length); }, 150);
					}

					self.setPostType('embed');

					container.find('.wall-textarea textarea').bind('focus', function(){
						$(this).bind('blur', {wallObject: self}, self.closeEmbedInput);
					});

					self.element.find('.wall-embed-input-empty').mousedown(function(){
						$(this).hide();
						var ta = $(this).siblings('.wall-textarea');
						ta.show();
						setTimeout(function(){ ta.find('textarea').focus(); }, 330);
					});

					/*
					 * Inline editing embed details
					 */
					self.element.find('.wall-post-area').addClass('embedded');
					self.element.find('.wall-post-share input').val('Share');

					var title_editor = new Enjin_Editor_Inline({
						el: self.element.find('.wall-post-area .post-panel.embed .embed-title'),
						save_response_mode: Enjin_Editor_Inline.SAVE_RESPONSE_REPLACE
					});
					var description_editor = new Enjin_Editor_Inline({
						el: self.element.find('.wall-post-area .post-panel.embed .embed-description'),
						type: 'textarea',
						save_response_mode: Enjin_Editor_Inline.SAVE_RESPONSE_REPLACE
					});

					self.element.find('.wall-post-area .post-panel.embed .embed-options input.embed-no-thumbnail').click(function() {
						if($(this).is(':checked'))
							self.element.find('.wall-post-area .post-panel.embed .embed-thumbnail-small-wrap').hide().parent().removeClass('with-thumbnail');
						else
							self.element.find('.wall-post-area .post-panel.embed .embed-thumbnail-small-wrap').show().parent().addClass('with-thumbnail');
					});
					self.element.find('.wall-post-area .post-panel.embed .embed-options input.embed-no-description').click(function() {
						if($(this).is(':checked'))
							self.element.find('.wall-post-area .post-panel.embed .embed-description').hide();
						else
							self.element.find('.wall-post-area .post-panel.embed .embed-description').show();
					});
				}
			}
		}, 'json');
	},
	
	showEmbedHTML: function(thumbnail) {
		var self = this;
		var parent = $(thumbnail).parents().eq(6);
		var w = parent.find('.embed').width();

		if(parent.attr('data-post_id'))
		{
			parent.find('.embed').load(self.url + '&cmd=show-embed-html&post_id=' + parent.attr('data-post_id') + '&width=' + w);
		}
	}
};


$(document).ready(function(){
    
    // auto load more results when scroling to the bottom of the page
    $(window).bind('scroll', function(){
        if ($(window).height() + $(window).scrollTop() >= $(document).height()){
            $('.m_system-profile, .m_system-dashboard, .m_system-siteprofile').find('.wall-viewmore:visible').trigger('click');
        }
    });
});