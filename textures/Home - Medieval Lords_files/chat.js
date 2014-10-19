/****
 * Chat Module
 * Copyright 2010 Enjin PTY LTD
 */

/*
 * Globals
 */
var chat = {};
var chat_data = {};

$(document).ready(function() {
	$('.m_chat').each(function() {
		var preset = $(this).attr('data-preset');
		if(chat[preset] == undefined) chat[preset] = new Chat(preset);
		chat[preset].populateChat(chat_data[preset].messages);
		chat[preset].populateUsers(chat_data[preset].users);
		setTimeout("chat[" + preset + "].getMessages();", 5000);
		
		$(this).find('.settings .clear-history a').click(function() {
			var confirmed = confirm('Are you sure you want to delete all chat history?');
			if(confirmed) chat[preset].clearHistory();
		});
		$(this).find('.settings .play-enable a').click(function() {
			chat[preset].optionPlay(true);
		});
		$(this).find('.settings .play-disable a').click(function() {
			chat[preset].optionPlay(false);
		});
		
		if (option_sound_play)
			$(this).find('.settings .play-enable').hide();
		else
			$(this).find('.settings .play-disable').hide();
	});
});

/*
 * Chat
 */
var Chat = function(id){
	this.id = id;
	if($('.m_chat[data-preset=' + this.id + ']').attr('data-timestamp') == '1') this.timestamp = true;
	
	//this.toolbar = new Enjin_BBCode_Toolbar(id, 'chatform_' + this.id, 'chat-input_'+id);
	//this.toolbar.setTextArea($('.m_chat[data-preset=' + this.id + '] input'));
}
Chat.prototype = {
	
	id: null,
	rendered_data: {},
	users: {},
	toolbar: {},
	timer: 0,
	last_message_id: 0,
	interval: 4000,
	mouse_moved: true,
	key_pressed: true,
	afk_time: 0,
	timestamp: false,
	
	postMessage: function(message)
	{
		var instance = this;
		
		message = instance.trim(message);
		var params = {cmd: 'post-message', preset_id: instance.id, message: message, last_message_id: instance.last_message_id};
		if(message)
		{
			$.ajax({
	    		type: "POST",
	    		url: "/ajax.php?s=chat",
	    		data: params,
	    		dataType: "json",
	    		success: function(data){
	    			if(data.success == 'false') {
						if (1 == data.code) {
							var message_html = '<div class="chat-entry chat-entry-red">' + '<span class="message">' + data.error + '</span></div>';
							instance.appendChatHtml(message_html);

						}
	    			} else {
	    				instance.populateChat(data.messages);
	    				instance.populateUsers(data.users, true);
	    			}
	    		},
	    		error: function (XMLHttpRequest, textStatus, errorThrown) {
	    		}
	    	});
	    }
	    $('.m_chat[data-preset=' + instance.id + '] input.chat-input').val('');
	},

	appendChatHtml: function(html) {
		var chat_content = $('.m_chat[data-preset=' + this.id + '] .chat-content');
		chat_content.append(html);
		chat_content.animate({scrollTop: chat_content[0].scrollHeight}, 0);
	},
	
	populateChat: function(data)
	{
		var instance = this;
		var elem = $('.m_chat[data-preset=' + instance.id + '] .chat-content');
		var bottom = false;
		if(elem[0].scrollHeight - elem.scrollTop() == elem.outerHeight()) bottom = true;
		
		var i;
		var html = '';
		for(i in data)
		{
			if(instance.rendered_data[data[i].message_id] == undefined && data[i].message_id != undefined)
			{
				instance.rendered_data[data[i].message_id] = data[i].message_id;
				instance.last_message_id = data[i].message_id;
				var timestamp = '';
				if(instance.timestamp) timestamp = '[' + data[i].time + '] ';
				html += '<div class="chat-entry">' + timestamp + '<span class="user">&lt;' + data[i].user + '&gt;</span><span class="message">' + data[i].message + '</span></div>';
			}
		}
		$('.m_chat[data-preset=' + instance.id + '] .chat-content').append(html);
		if(bottom == true && scroll != false) elem.animate({scrollTop: elem[0].scrollHeight}, 0);
	},
	
	populateUsers: function(data, notify)
	{
		var instance = this;
		if(instance.users[instance.id] == undefined) instance.users[instance.id] = {};
		
		var elem = $('.m_chat[data-preset=' + instance.id + '] .chat-users');
		
		var i;
		var html = '';
		for(i in data)
		{
			if(instance.users[instance.id][data[i].user_id] == undefined)
			{
				instance.users[instance.id][data[i].user_id] = data[i];
				//if(notify == true) instance.displayMessage('<div class="chat-entry joined">*** ' + data[i].user + ' has joined chat.</div>');
			}
		}
		for(i in instance.users[instance.id])
		{
			if(data[instance.users[instance.id][i].user_id] == undefined)
			{
				//if(notify == true) instance.displayMessage('<div class="chat-entry left">*** ' + instance.users[instance.id][i].user + ' has left chat.</div>');
				delete instance.users[instance.id][i];
			}
			else html += '<div class="user">' + instance.users[instance.id][i].user + '</div>';
		}
		elem.html(html);
	},
	
	getMessages: function()
	{
		var instance = this;
		
		$.ajax({
    		type: "GET",
    		url: "/ajax.php?s=chat",
    		data: {cmd: 'get-updates', preset_id: this.id, last_message_id: this.last_message_id},
    		dataType: "json",
    		success: function(data) {
    			if(data.success == 'false') {}
    			else {
    				instance.populateChat(data.messages);
    				instance.populateUsers(data.users, true);
    				
    				if (option_sound_play
    						&& (typeof data.messages.length == 'undefined')) {    					
    					Enjin_Core.playSound(smanager_mp3path);
    				}
    			}
    		},
    		error: function (XMLHttpRequest, textStatus, errorThrown) {
    		}
    	});
    	
    	instance.resetTimer();
		instance.afk_time += instance.interval;
		
		if(instance.mouse_moved) {
			$('body').one('mousemove', function(e) {
				instance.mouse_moved = true;
				instance.afk_time = 0;
				instance.resetTimer();
			});
		}
		if(instance.key_pressed) {
			$('body').one('keypress', function(e) {
				instance.key_pressed = true;
				instance.afk_time = 0;
				instance.resetTimer();
			});
		}
		instance.mouse_moved = false;
		instance.key_pressed = false;
	},
	
	resetTimer: function() {
		var instance = this;
		clearTimeout(instance.timer);
		
		var interval_time = instance.interval;
    	if(instance.afk_time > 30000) interval_time = 30000;
		instance.timer = setTimeout("chat[" + this.id + "].getMessages();", interval_time);
	},
	
	clearHistory: function() {
		var instance = this;
		$.ajax({
    		type: "POST",
    		url: "/ajax.php?s=chat",
    		data: {cmd: 'clear-history', preset_id: this.id},
    		dataType: "json",
    		success: function(data) {
    			if(data.success == 'false') {}
    			else {
    				instance.clearMessages();
    			}
    		},
    		error: function (XMLHttpRequest, textStatus, errorThrown) {
    		}
    	});
	},
	
	clearMessages: function() {
		$('.m_chat[data-preset=' + this.id + '] .chat-content').html('');
	},
	
	displayMessage: function(message, scroll) {
		var elem = $('.m_chat[data-preset=' + this.id + '] .chat-content');
		var bottom = false;
		if(elem[0].scrollHeight - elem.scrollTop() == elem.outerHeight()) bottom = true;
		
		$('.m_chat[data-preset=' + this.id + '] .chat-content').append(message);
		
		if(bottom == true && scroll != false) elem.animate({scrollTop: elem[0].scrollHeight}, 0);
	},
	
	optionPlay: function(flag) {
		var el = $('.m_chat[data-preset=' + this.id + ']');
		var data = {
			cmd: 'set-option', 
			preset_id: this.id,
			option: 'play_sound',
			value: flag?'1':'0'
		};
				
		$.post("/ajax.php?s=chat",
    		data,
    		function(response) {
				option_sound_play = flag;
    		},
    	'json');
    	
    	if (flag) {
 			el.find('.settings .play-enable').hide();
 			el.find('.settings .play-disable').show();
 		} else {
 			el.find('.settings .play-disable').hide();
 			el.find('.settings .play-enable').show();
 		}
	},
	
	trim: function(str) {
		return str.replace(/^\s+|\s+$/g,"");
	}
}