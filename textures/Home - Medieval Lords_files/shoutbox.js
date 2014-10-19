Module_Shoutbox_DataStore = function(shoutbox, preset_id, options) {
	this.init(shoutbox, preset_id, options);	
}


Module_Shoutbox_DataStore.__id = 0;
Module_Shoutbox_DataStore.__ids = {};
Module_Shoutbox_DataStore.get = function (store_id) {
	return Module_Shoutbox_DataStore.__ids[store_id];
}
Module_Shoutbox_DataStore.save = function(datastore) {
	var cid = Module_Shoutbox_DataStore.__id++; 
	Module_Shoutbox_DataStore.__ids[cid] = datastore;
	
	return cid;
}


Module_Shoutbox_DataStore.prototype = {
		preset_id: null,
		options: null,
		page: 1,
		total_pages: null,
		
		shouts: null,
		html_shouts: null,
		html_paginationCurrentPage: null, //current page
		html_paginationTotalPages: null, //current page
		html_paginationLinkPrev: null,
		html_paginationLinkSeparator: null,
		html_paginationLinkNext: null,
		
		item_shouts: null,
		shoutbox: null, //parent
		store_id: null,
		
		init: function(shoutbox, preset_id, options) {
			var i=0;
			var shout;
			var shouts = options.shouts;
			var mc = null;
			
			this.shoutbox = shoutbox;
			this.item_shouts = [];
			this.shouts = [];
			
			this.preset_id = preset_id;
			this.options = options;
			
			this.total_pages = this.options.total_pages;
			
			mc = $('.m_shoutbox_'+preset_id);
			this.html_shouts =  mc.find('.shouts');
			this.html_pagination = mc.find('.pagination');
			this.html_paginationCurrentPage = mc.find('.pagination .info .currentPage');
			this.html_paginationTotalPages = mc.find('.pagination .info .totalPages');
			this.html_paginationLinkPrev = mc.find('.pagination .controls .prev');
			this.html_paginationLinkSeparator = mc.find('.pagination .controls .separator');
			this.html_paginationLinkNext = mc.find('.pagination .controls .next');
			this.store_id = Module_Shoutbox_DataStore.save(this);
			
			for (i=shouts.length-1; i>=0; i--) {
				shout = shouts[i];
				this.addItem(shout);
			}
			
			this.updatePagination();
		},	
		
		addItem: function(shout, shout_html) {
			var shout_item;
			var shout_check;
			var i;
			var self = this;
			
			shout.shout_id = parseInt(shout.shout_id);
			if (shout_html) {
				for (i=0; i<this.shouts.length; i++) {
					shout_check = this.shouts[i].shout;
					
					if (shout_check.shout_id == shout.shout_id) {
						return;
					}
				}
			}
			
			this.shoutbox.updateLastID(shout.shout_id);
			
			if (this.shouts.length == 0) {
				if (shout_html) {
					//create new
					this.html_shouts.append(shout_html);
				}
				
				this.shouts.push({item: shout_item, shout: shout});
			} else {
				
				if (shout_html) {
					this.html_shouts.prepend(shout_html);
				}
				this.setItemAt({item: shout_item, shout: shout}, 0);
			}
			
			//add events
			if (this.options.is_admin) {
				this.html_shouts.find('.shout_'+shout.shout_id+' .x').bind('click', {shout_id: shout.shout_id}, function(evt) {
					var el = $(this).closest('.shout');
					
					if (confirm('Are you sure to delete this shoutbox entry?')) {				
						el.fadeOut(400, function(){
							el.remove();
							self.removeShout(evt.data.shout_id);
						});
					}			
				});
			}
		},
		
		setItemAt: function(item, index) {
			var i;
			//var _shouts = [];
			var max_items = 15 * this.options.items_page;
			this.shouts.splice(index, 0, item);
			
			if (this.shouts.length > max_items)
				this.shouts.splice(max_items, this.shouts.length-max_items);
		},
		
		removeItem: function(shout_id) {
			var i;
			var index = -1;
			
			for (i=0; i<this.shouts.length; i++) {
				if (this.shouts[i].shout.shout_id == shout_id) {
					index = i;
					break;
				}				
			}
			
			if (index > -1)
				this.shouts.splice(index, 1);
		},		
		
		updatePagination: function() {
			var haveLinkPrev = false;
			var haveLinkNext = false;
			
			this.total_pages = parseInt(Math.ceil(this.shouts.length / (this.options.items_page*1.0)));
			this.total_pages = Math.max(0, this.total_pages);
			
			//can happen when remove all items on a page
			if (this.page > this.total_pages) {
				this.page = this.total_pages;
			}
			
			this.html_paginationCurrentPage.html(this.page);
			this.html_paginationTotalPages.html(this.total_pages);
			
			this.html_paginationLinkPrev.css('display', 'none');
			this.html_paginationLinkSeparator.css('display', 'none');
			this.html_paginationLinkNext.css('display', 'none');
			
			if (this.page > 1) {
				haveLinkPrev = true;
				this.html_paginationLinkPrev.css('display', 'inline');
			}
			
			if (this.page < this.total_pages) {
				haveLinkNext = true;
				this.html_paginationLinkNext.css('display', 'inline');
			}
			
			
			if (haveLinkPrev && haveLinkNext) {
				this.html_paginationLinkSeparator.css('display', 'inline');
			}
			
			if (this.total_pages > 1) {
				this.html_pagination.css('display', 'block');
			} else {
				this.html_pagination.css('display', 'none');
			}
			
			//show only items items
			this.showPageItems();
		},
		
		showPageItems: function() {			
			var i = 0;
			var shout;
			var start_index = (this.page-1)*this.options.items_page;
			var end_index = ((this.page)*this.options.items_page);
			
			end_index = Math.min(end_index, this.shouts.length);
			
			this.html_shouts.find('.shout:lt('+start_index+')').hide();			
			this.html_shouts.find('.shout:gt('+ (end_index-1) +')').hide();
			for (i=start_index; i<end_index; i++) {
				this.html_shouts.find('.shout:eq('+i+')').show();
			}			
		},
		
		pagePrev: function() {
			if (this.page > 1) {
				this.page--;
				this.updatePagination();
			}
		},
		
		pageNext: function() {
			if (this.page < this.total_pages) {
				this.page++;
				this.updatePagination();
			}
		},
		
		goPage: function(page) {
			if (page > 0 && page <= this.total_pages) {
				this.page = page;
				this.updatePagination();
			}
		},
		
		removeShout: function(shout_id) {
			this.removeItem(shout_id);
			this.updatePagination();
			this.shoutbox.removeShout(shout_id);
		}
}





Module_Shoutbox = function(preset_id, options) {
	if (typeof Module_Shoutbox.__instance[preset_id] == 'undefined') {
		this.init(preset_id, options);
		Module_Shoutbox.__instance[preset_id] = this;
	}
}

Module_Shoutbox.__instance = [];
Module_Shoutbox.getInstance = function(preset_id) {
	return Module_Shoutbox.__instance[preset_id];
}

Module_Shoutbox.prototype = {
	msg_name: 		'Your name',	
	msg_message: 	'Your message',
		
	preset_id: null,
	options: null,
	
	allowPosting: null,
	dataStore: null,	
	last_id: null,
	
	init: function(preset_id, options) {
		this.preset_id = preset_id;
		this.options = options;
		this.shouts = options.shouts;
		this.allowPosting = true;
		this.last_id = options.last_id;
		
		this.initListeners();
		
		this.dataStore = new Module_Shoutbox_DataStore(this, preset_id, options);
	},	
	
	pagePrev: function() {
		this.closeError();
		this.dataStore.pagePrev();
	},
	pageNext: function() {
		this.closeError();
		this.dataStore.pageNext();
	},
	
	updateLastID: function(last_id) {
		if (last_id > this.last_id)
			this.last_id = last_id;
	},
	
	initListeners: function() {
		var self = this;
		
		//maybe we can remove the id on this since it can be 
		var input_name = $('.m_shoutbox_'+this.preset_id+' .form_post input[name=name]'); 
		var input_msg = $('.m_shoutbox_'+this.preset_id+' .form_post input[name=message]'); 
		
		input_name.val(this.msg_name);
		input_msg.val(this.msg_message);		
		
		this.prepareListener(input_name, this.msg_name);
		this.prepareListener(input_msg, this.msg_message);
	},
	
	prepareListener: function(input, message) {
		$(input).bind('focus', function() {
			if (!$(this).data('changed')) {
				$(this).val('');
			}
			
			$(this).addClass('writing');
		});

		$(input).bind('blur', function() {
			var text = jQuery.trim($(this).val());
			
			if (text != '') {
				$(this).data('changed', true);
			} else {
				$(this).data('changed', false);
				$(this).val(message);
			}
			
			$(this).removeClass('writing');
		});
		
		$(input).keydown(function(event){
			if (event.keyCode == 13) {
				$(this).blur();
				$(this).parent().parent().find('.button-submit input').click();
			}
		});		
	},
	
	submit: function(element) {
		if (!this.allowPosting) {
			alert('You must wait before post again');
			return; //cannot post
		}
		
		var errors = [];
		var formElement = $(element).closest('.form_post');
		var input_name = formElement.find('.item-name :text');
		var input_msg = formElement.find('.item-msg :text');
		
		if (input_name.length) {
			if (!input_name.data('changed') || jQuery.trim(input_name.val()) == '')
				errors.push('You must write your name');			
		}
				
		if (!input_msg.data('changed') || jQuery.trim(input_msg.val()) == '') {
			errors.push('You must write a message');
		}
		
		if (errors.length == 0) {
			//disable shoutbox
			var self = this;
			var value_name = input_name.val();
			var value_msg = input_msg.val();
			
			this.closeError();
			
			//clean
			input_msg.val(self.msg_message);
			input_msg.data('changed', false);
			
			//post through ajax			
			$.post(location.href, 
					{m: this.preset_id, response: 'ajax', postback: true, name: value_name, 
						message: value_msg, last_id: this.last_id},
					function(response) {							
						if (response.error.length == 0) {
							var i=0;
							self.disableInput();							
							self.updateLastID(response.last_id);
							
							//process items
							for (i=0; i<response.shouts_html.length; i++) {
								self.dataStore.addItem(response.shouts[i], response.shouts_html[i]);
							}
							
							//go to first item
							self.dataStore.updatePagination();
							self.dataStore.goPage(1);							
							
						} else {
							self.showError(response.error.join("<br />"));
						}
					}, 'json');
		} else {
			this.showError(errors.join("<br />"));			
		}
	},
	
	showError: function(message) {
		$('.m_shoutbox_'+this.preset_id+' .error').html(message);
		$('.m_shoutbox_'+this.preset_id+' .error').css('display', 'block');
	},
	
	closeError: function() {
		$('.m_shoutbox_'+this.preset_id+' .error').css('display', 'none');
	},
	
	refresh: function() {
		var self = this;
		this.closeError();
		
		$.post(location.href, 
				{m: this.preset_id, response: 'ajax', last_id: this.last_id},
				function(response) {							
					if (response.error.length == 0) {
						var i=0;
						
						//process items
						for (i=0; i<response.shouts_html.length; i++) {							
							self.dataStore.addItem(response.shouts[i], response.shouts_html[i]);
						}
						
						//go to first item
						if (response.shouts_html.length) {
							self.dataStore.goPage(1);
							self.updateLastID(response.last_id);
						}
					} else {
						self.showError(response.error.join("<br />"));
					}
				}, 'json');		
	},
	
	disableInput: function() {
		var self = this;
		$('.m_shoutbox_'+this.preset_id+' .form_post :text').attr('disabled', true);
		this.allowPosting = false;		
		$('.m_shoutbox_'+this.preset_id+' .form_post .button-submit').addClass('disabled');
		
		setTimeout(function() {
			self.enableInput();
		}, this.options.disable_time);
	},
	
	enableInput: function() {
		$('.m_shoutbox_'+this.preset_id+' .form_post .button-submit').removeClass('disabled');
		$('.m_shoutbox_'+this.preset_id+' .form_post :text').attr('disabled', false);
		this.allowPosting = true;
	},
	
	removeShout: function(shout_id) {
		var self = this;
		
		$.post(location.href, 
				{m: this.preset_id, response: 'ajax', op: 'delete', shout_id: shout_id},
				function(response) {							
					if (response.error.length != 0) {
						self.showError(response.error.join("<br />"));
					}
				}, 'json');		
	}
}
