Enjin_GallerySlideshow = function(preset_id, data, images, url_detail) {
	this.init(preset_id, data, images, url_detail);
}

Enjin_GallerySlideshow.__instancesPreset = {};
Enjin_GallerySlideshow.getInstanceForPreset = function(preset_id) {	
	return Enjin_GallerySlideshow.__instancesPreset[preset_id];
}

Enjin_GallerySlideshow.prototype = {
	preset_id: null,
	data: null,
	current_index: null,
	current_index_showing: null,
	el_main: null,
	el_main_img: null,
	timeout_id: null,
	playing: null,
	loaded_first_image: null,
	waiting_timeout: false,
	show_navs: false,
	url_detail: null,
	images: null,
		
	init: function(preset_id, data, images, url_detail) {
		Enjin_GallerySlideshow.__instancesPreset[data.gallery_preset_id] = this;
	
		var self = this;
		this.preset_id = preset_id;
		this.data = data;		
		this.current_index = 0;
		this.current_index_showing = 0;
		this.loaded_first_image = false;		
		
		this.el_main = $('.m_galleryslideshow_'+preset_id);
		this.el_main_img = this.el_main.find('img');
		this.timeout_id = 0;
		this.playing = true;
		this.images = images;
		this.url_detail = url_detail;
		
		//check if have more than 1 image
		if (this.images.length > 1)
			this.show_navs = true;
		
		
		//prepare events
		this.el_main_img.bind('load', function() {
			if (!self.loaded_first_image) {
				var top = Math.floor((self.el_main_img.height()-30)*0.5);
				self.el_main.find('a.previous').css('top', top);
				self.el_main.find('a.next').css('top', top);
				self.loaded_first_image = true;
			}			
			self.callTimeout();
		});
		this.el_main_img.bind('error', function() {
			self.el_main_img.show();
			self.callTimeout();
		});
		this.el_main.find('a').bind('mouseover', function() {
			self.stopTimeout();
			self.playing = false;
			
			if (self.show_navs) {
				self.el_main.find('a.previous').show();
				self.el_main.find('a.next').show();
			}
		});
		this.el_main.find('a').bind('mouseout', function() {
			self.playing = true;
			self.callLoadImage();
			
			if (self.show_navs) {
				self.el_main.find('a.previous').hide();
				self.el_main.find('a.next').hide();
			}
		});
		
		this.el_main.find('a.previous').bind('click', function(event) {
			event.preventDefault()
			event.stopPropagation()
			self.goPrevious();
		});
		this.el_main.find('a.next').bind('click', function(event) {
			event.preventDefault()
			event.stopPropagation()
			self.goNext();
		});
				
		
		
		/*
		this.el_main.find('a.preview').bind('click', function() {
			var pd = Enjin_Gallery_Popup_Detail.getInstance(self.data.gallery_preset_id);
			var image = pd.getImageIndex(self.current_index_showing);
						
			if (self.data.open_popup == "1") {
				//call to popup
				var url = '/popup/m/'+self.data.gallery_preset_id+'/detail/'+image.image_id;
				this.popup_window = window.open(url,'gallery_detail','location=0,status=0,scrollbars=1,resizable=0,menubar=0,height=720,width=700');
			} else {
				window.location = '/'+self.data.page_url+'/m/'+self.data.gallery_preset_id+'/detail/'+image.image_id;
			}
			
			pd.detailShow(image.image_id);
		});	
		*/	
		
		if ($.browser.msie) {
			//need to set explicit width/height
			this.el_main_img.css('width', this.el_main.width()+"px");			
		}
		this.el_main_img.show();
		
		this.loadImage();
	},
	
	stopTimeout: function() {
		if (this.timeout_id > 0) {
			clearTimeout(this.timeout_id);
			this.timeout_id = 0;
		}
	},
	
	callTimeout: function() {		
		var self = this;
		this.current_index++;
		
		if (this.current_index >= this.images.length)
			this.current_index = 0;
		
		this.waiting_timeout = true;
		this.callLoadImage();
	},
	
	callLoadImage: function() {
		var self = this;
		if (this.timeout_id > 0)
			this.stopTimeout();
			
		this.timeout_id = setTimeout(function() {
			if (self.playing)
				self.loadImage();
		}, parseInt(this.data.seconds)*1000);
	},
	
	goNext: function() {
		this.stopTimeout();
		
		if (!this.waiting_timeout) {
			this.current_index++;
			if (this.current_index >= this.images.length)
				this.current_index = 0;
		}
		
		this.loadImage();
	},
	
	goPrevious: function() {
		this.stopTimeout();
		this.current_index = this.current_index_showing - 1;
		
		if (this.current_index < 0)
			this.current_index = this.images.length-1;
		
		this.loadImage();
	},	
	
	loadImage: function() {
		this.waiting_timeout = false;
		var image = this.images[this.current_index];
		var href = this.url_detail.replace('ALBUM_ID', image.album_id).replace('PHOTO_ID', image.image_id);
		
		this.current_index_showing = this.current_index;
		this.el_main_img.attr('src', image.url_full);
		this.el_main.find('a.preview').attr('href', href);
	},
	
	removeImage: function(image_id) {
		this.stopTimeout();
		this.playing = false;
		var images_length = this.images.length
		var changed_index = false;
		var image = this.images[this.current_index];
		
		if (image.image_id == image_id) {
			this.current_index = 0;
			changed_index = true;
		}

		if (this.current_index > images_length) {
			this.current_index = 0;
			changed_index = true;
		}
		
		if (images_length > 0) {
			//start playing again
			this.playing = true;
			
			if (changed_index)
				this.loadImage();
			else
				this.callLoadImage();
				
		} else {
			//hide all
			this.el_main.hide();
		}
	},
	
	addImage: function() {
		this.playing = false;
		this.stopTimeout();		
		
		//always will be at start
		if (this.current_index == 0) {
			this.current_index = 2;
		}
		
		this.playing = true;
		this.callLoadImage();
	}
}
