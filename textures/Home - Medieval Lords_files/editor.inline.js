
Enjin_Editor_Inline = function(options) {
	this.init(options);
}

Enjin_Editor_Inline.SAVE_RESPONSE_NONE = 'save_response_none';
Enjin_Editor_Inline.SAVE_RESPONSE_REPLACE = 'save_response_replace';
Enjin_Editor_Inline.SAVE_RESPONSE_CONTENT = 'save_response_content';
Enjin_Editor_Inline.SAVE_RESPONSE_AJAX = 'save_response_ajax';

Enjin_Editor_Inline.prototype = {
	options: null,
	el: null,
	editor_el: null,
	default_value: null,
	
	init: function(options) {
		var self = this;
		var default_options = {
			el: null,
			url_params: {},
			url_save: '',
			url_save_method: 'POST',
			save_response_mode: Enjin_Editor_Inline.SAVE_RESPONSE_CONTENT,
			blur: function() {
				self.save();
			},
			
			editor_class: 'editor-inline',
			editor_attrs: {},
			type: 'text',
			show_buttons: false
		};
		
		jQuery.extend(default_options, options);
		
		this.options = default_options;
		this.el = $(options.el);				
		this.default_value = this.el.html();
		this.addEvents();
	},
	
	onClick: function(evt) {
		var self = evt.data.scope;
		self.showEditor();
	},
	
	addEvents: function() {
		this.el.bind('click', {scope: this}, this.onClick);		
	},
	
	removeEvents: function() {
		this.el.unbind('click', this.onClick);
	},
	
	getEditor: function() {
		var _el;
		var self = this;
		
		if (this.options.type == 'text') {
			var value = Enjin_Core.html_entity_decode(this.default_value);
			_el = $(document.createElement('input'));
			_el.attr('type', 'text');
			_el.val(value);
		}
		else if (this.options.type == 'textarea') {
			var value = Enjin_Core.html_entity_decode(this.default_value);
			_el = $(document.createElement('textarea'));
			_el.val(value);
		}
		
		_el.addClass(this.options.editor_class);		
		jQuery.each(this.options.editor_attrs, function(key, value) {
			_el.attr(key, value);
		});
		
		//@todo add buttons if needed
		
		//add events
		_el.bind('blur', self.options.blur);
		_el.bind('keydown', function(evt) {
			if (evt.keyCode == 0x1B) {
				self.closeEditor();				
			}
			
			if (evt.keyCode == 0xD) {
				self.save();
			}
		});
		
		this.editor_el = _el;
		return _el;
	},
	
	showEditor: function() {
		//replace content with new editor
		this.removeEvents();
		$(this).trigger('beforeRenderEditor');

		if(this.options.type == 'textarea')
			this.el.html('<div class="input-textarea"></div>').find('.input-textarea').append(this.getEditor());
		else
			this.el.html('<div class="input-text"></div>').find('.input-text').append(this.getEditor());
		
		this.el.parent().css('-moz-user-select', null);
		this.el.parent().removeClass('x-unselectable');
		
		$(this).trigger('startEdit');

		var element = this.el;
		setTimeout(function(){element.find('textarea, input').focus()}, 200);
	},
	
	closeEditor: function(text) {
		if (!text) {
			text = this.default_value;
		}
		
		text = Enjin_Core.htmlentities(text);
		this.el.html(text);
		this.addEvents();
		$(this).trigger('stopEdit');
	},
	
	getVal: function(disable_escaped) {
		if (disable_escaped)
			return this.editor_el.val();
		else
			return Enjin_Core.htmlentities(this.editor_el.val()); 
	},
	
	save: function() {
		var self = this;
		var content = this.getVal(true);		
		
		switch(this.options.save_response_mode) {
			case Enjin_Editor_Inline.SAVE_RESPONSE_NONE:
				//just trigger
				$(this).trigger('save', {text: content});
				this.closeEditor();
				break;

			case Enjin_Editor_Inline.SAVE_RESPONSE_REPLACE:
				//set the html
				if($.trim(content).length == 0) content = self.default_value;
				self.default_value = content;
				self.closeEditor();
				break;

			case Enjin_Editor_Inline.SAVE_RESPONSE_CONTENT:
				//call to ajax save url and set the html
				var params = {};
				jQuery.extend(params, this.options.url_params);
				jQuery.extend(params, {text: content});
				
				$.ajax({
					url: this.options.url_save,
					type: this.options.url_save_method,
					data: params,
					complete: function(request, status) {
						self.closeEditor();
					},
					dataType: 'html',
					cache: false,
					success: function(data, status) {
						self.default_value = data;
					}
				});
				break;
				
			case Enjin_Editor_Inline.SAVE_RESPONSE_AJAX:
				//call to ajax save url and set the html
				var params = {};
				jQuery.extend(params, this.options.url_params);
				jQuery.extend(params, {text: content});
				
				$.ajax({
					url: this.options.url_save,
					type: this.options.url_save_method,
					data: params,
					complete: function(request, status) {
						self.closeEditor();
					},
					dataType: 'json',
					cache: false,
					success: function(response, status) {
						if (response.error == 0) {
							self.default_value = response.data;
						} else {
							var message = response.data;
							if (typeof response.success != 'undefined')
								message = response.error;
							
							Enjin_Core.alert(message);
						}
					}					
				});
				break;				
		}
	}
}