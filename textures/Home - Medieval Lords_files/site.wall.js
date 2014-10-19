function Enjin_Site_Wall() {
}
Enjin_Site_Wall.viewMorePosts = function(link) {
	var posts = $(link).siblings('.posts');
	
	$(link).hide().blur();
	$(link).after("<div class='viewmore-loading'>Loading ...</div>");

	var oldest_forum_thread = posts.children('.wall-post.forum_thread_group:last').attr('data-oldest');
	if(oldest_forum_thread > 1) oldest_forum_thread = '/oldest_forum_thread/' + oldest_forum_thread;
	else oldest_forum_thread = '';

	$.ajax({
		type: "GET",
		url: url_getposts + '/var1/' + posts.children('div:last').attr('data-timestamp') + oldest_forum_thread,
		dataType: "json",
		success: function(data){
			if(data.error !== undefined) alert('Error', data.error);
			else {
				posts.append($(data.content));
				posts.children('.wall-post').fadeIn(600);
				$(link).siblings('.viewmore-loading').remove();
				$(link).show();
				if(posts.children('.site_created').length) $(link).hide();
			}
   		},
   		error: function (XMLHttpRequest, textStatus, errorThrown) {
   		}
   	});
}

Enjin_Site_Wall.likeWallPost = function(link, cmd) {
	var comments = $(link).parent().parent().parent().parent().children('.comments');
	var state = $(link).attr('data-state');
	if(state == 1) {
		state = 0;
		$.post('/ajax.php?s=sitewall', {cmd: 'unlike-' + cmd, post_id: $(link).parent().parent().parent().parent().parent().parent().parent().parent().attr('data-post_id')});
		$(link).html('Like').attr('data-state', state).blur();
		var avatars = comments.children('.users-liked').children('.element_avatar');
		avatars.filter('.yourself').remove();
		if(comments.children('.users-liked').children('.element_avatar').length == 0) comments.children('.users-liked').remove();
		if(comments.children('.cbox:visible').length == 0) comments.children('.triangle').hide();
	}
	else {
		state = 1;
		$.post('/ajax.php?s=sitewall', {cmd: 'like-' + cmd, post_id: $(link).parent().parent().parent().parent().parent().parent().parent().parent().attr('data-post_id')});
		$(link).html('Unlike').attr('data-state', state).blur();
		if(comments.children('.users-liked').length == 0) {
			comments.children('.first').removeClass('first');
			if(comments.children('.cbox:visible').length == 0) {
				comments.children('.last').removeClass('last');
				var cls = ' last';
			}
			else var cls = '';
			comments.children('.triangle').show().after("<div class='users-liked cbox first" + cls + "'><span>Liked this</span></div>");
		}
		var ava = $('#enjin-bar').children('.right').children('.mini-avatar').children('.element_avatar').clone();
		ava.addClass('yourself');
		comments.children('.users-liked').children('span').before(ava);
		comments.show();
	}
}