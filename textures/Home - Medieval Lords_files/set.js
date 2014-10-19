// ----------------------------------------------------------------------------
// markItUp!
// ----------------------------------------------------------------------------
// Copyright (C) 2008 Jay Salvat
// http://markitup.jaysalvat.com/
// ----------------------------------------------------------------------------
// BBCode tags example
// http://en.wikipedia.org/wiki/Bbcode
// ----------------------------------------------------------------------------
// Feel free to add more tags
// ----------------------------------------------------------------------------
if ( typeof MarkItUp !== 'object' ) { MarkItUp = {}; }
MarkItUp.BBCode = {
	previewParserPath:	'', // path to your BBCode parser
	resizeHandle: false,
	previewAutoRefresh: false,
	
	buttons:  "removeformat size color smiley link quote image youtube attach spoiler email code noparse warning | " +
	          "bold italic underline highlight strikethrough superscript subscript alignleft center alignright justify bullets numbering outdent indent table line columns",
	
	markupSet: {
            	"removeformat": {name:'Clean', className:"clean", buttonIndex:0, customWidth:26, replaceWith:function(markitup) { return markitup.selection.replace(/\[(.*?)\]/g, "") } },
        		"size": {name:'Size', key:'S', 
        		         popupContent: function()
        		                        {
        		                            var html = '<div class="sizes"><table>' + 
                                                			'<tr class="target" data-open="[size=1]" data-close="[/size]"><td class="txt" width="50px">Tiny</td><td class="a" style="font-size: 0.67em;">A</td></tr>' +
                                                			'<tr class="target" data-open="[size=2]" data-close="[/size]"><td class="txt">Small</td><td class="a" style="font-size: 0.83em;">A</td></tr>' +
                                                			'<tr class="target" data-open="[size=3]" data-close="[/size]"><td class="txt">Normal</td><td class="a">A</td></tr>' +
                                                			'<tr class="target" data-open="[size=5]" data-close="[/size]"><td class="txt">Large</td><td class="a" style="font-size: 1.5em;">A</td></tr>' +
                                                			'<tr class="target" data-open="[size=6]" data-close="[/size]"><td class="txt">Huge</td><td class="a" style="font-size: 2em;">A</td></tr>' +
                                                		'</table></div>';
                                            return html;
        		                        }
        		        ,buttonIndex:1
        		        ,customWidth:52
                        },
              "color": { name:'Colors',     
                         popupContent: function() 
                                        { 	

                                            var html = '<div class="colors">';
                                    		var colors = [
                                                			'eeeeee', 'bebebe', '989898', '7a7a7a', '4e4e4e', '3e3e3e', '282828', '181818',
                                                			'f3b2b1', 'f3d9b1', 'f3efb1', 'caf3b1', 'b1dcf3', 'b1c6f3', 'ccb1f3', 'eeacf9',
                                                			'dd2423', 'dd9323', 'ddd123', '6cdd23', '239edd', '2360dd', '6e23dd', 'd113ee',
                                                			'761413', '764f13', '767013', '3a7613', '135476', '133376', '3b1376', '6f0a7f'
                                                        ];
		                                    for ( var i = 0; i < colors.length; i++ ) { html += '<div data-open="[color=#' + colors[i] + ']" data-close="[/color]" class="swatch target" style="background: #' + colors[i] + ';" <!-- --></div>'; }
		                                    return html + "</div>";
                                        }, 
                         buttonIndex:2
                       },
        	"smiley": { name:'Insert Smiley'
        	           ,popupContent: function(options)
        	                          {
        	                             var html = '<div class="smileys">';
        	                             for ( var s in options.smileys )
        	                             {
        	                                //var text = options.smileys[s].text.replace('"','&quot;');
											 var text = $('<pre>').text(options.smileys[s].text).html();
        	                                html += '<img data-open=" ' + text + ' " class="smiley_icon target" src="' + options.smileys[s].icon + '" title="' + text + '" >';
        	                             }
        	                             html += '</div>';
        	                             return html;
        	                          }
        	           ,buttonIndex:3
        	          },
        	"image": { name:'Image'      
        	          ,popupPrompt: 'Image URL'
        	          ,popupButton: 'Insert Image'
        	          ,isURL: true
        	          ,replaceWith:'[img]{input}[/img]'
        	          ,buttonIndex:4
        	         },
        	"link": { name:'Link'                   
        	         ,popupPrompt: 'Link URL'
        	         ,popupButton: 'Insert Link'
        	         ,isURL: true
        	         ,openWith:'[url={input}]'
        	         ,closeWith:'[/url]'                        
        	         ,placeHolder:'Your text to link here...'
        	         ,buttonIndex:5
        	        },
        	"quote": {name:'Quote'      
        	         ,popupType: 'search-users'	
					 ,popupPrompt: 'Quoted From'
        	         ,popupButton: 'Insert Quote'
        	         ,openWith:'[quote={input}]'
        	         ,closeWith:'[/quote]'
        	         ,buttonIndex:6
        	        },
        	"code": {name:'Code', openWith:'[code]', closeWith:'[/code]', buttonIndex:7},
        	"spoiler": {name:'Spoiler'              
        	           ,popupPrompt: 'Spoiler Title'
        	           ,popupButton: 'Insert Spoiler'
        	           ,openWith:'[spoiler={input}]'
        	           ,closeWith:'[/spoiler]'
        	           ,buttonIndex:18
        	          },
        	"bold": {name:'Bold', key:'B', openWith:'[b]', closeWith:'[/b]', buttonIndex:9, customWidth:26 },
        	"italic": {name:'Italic', key:'I', openWith:'[i]', closeWith:'[/i]', buttonIndex:10},
        	"underline": {name:'Underline', key:'U', openWith:'[u]', closeWith:'[/u]', buttonIndex:11},
        	"strikethrough": {name:'Strikethrough',  openWith:'[s]', closeWith:'[/s]', buttonIndex:12},
        	"alignleft": {name:'Align Left',  openWith:'[left]', closeWith:'[/left]', buttonIndex:13},
        	"center": {name:'Align Center',  openWith:'[center]', closeWith:'[/center]', buttonIndex:14},
        	"alignright": {name:'Align Right',  openWith:'[right]', closeWith:'[/right]', buttonIndex:15},
        	"bullets": {name:'Bulleted list', 
                         replaceWith:function(markItUp) {
                            var s = markItUp.selection.split( /\r?\n/ );
                            var output = '[list]\n';
                            for ( var i = 0; i < s.length; i++ ) { output += '[*] ' + jQuery.trim(s[i]) + "\n"; } 
                            return output + "[/list]";
                         },     
						 /** 
						  * Offset moves the caret back [n] characters from the end of the string
						  * in this case we move back 8 characters (to the [*] for item entering) 
						  * unless the user marked an existing content, in which case we do nothing
						  * an leave caret at the end of the tag set
						  */						 
						 caretOffset: function( markItUp ) {  
								return ( markItUp.selection == "" ? ($(markItUp.textarea).attr('type') === 'text' ? -10 : -8) : 0 );
							},
                         buttonIndex:16
        	           },
        	"numbering": {name:'Numeric list', 
                         replaceWith:function(markItUp) { 
                            var s = markItUp.selection.split( /\r?\n/ );
                            var output = '[list=1]\n';
                            for ( var i = 0; i < s.length; i++ ) { output += '[*] ' + jQuery.trim(s[i]) + "\n"; } 
                            return output + "[/list]";
                         },      
						 /** 
						  * Offset moves the caret back [n] characters from the end of the string
						  * in this case we move back 8 characters (to the [*] for item entering) 
						  * unless the user marked an existing content, in which case we do nothing
						  * an leave caret at the end of the tag set
						  */						 
						 caretOffset: function( markItUp ) { 
								return ( markItUp.selection == "" ? ($(markItUp.textarea).attr('type') === 'text' ? -10 : -8) : 0 );
							},
                         buttonIndex:17
                        }, 
        	"outdent": {name:'Decrease Indent', openWith:'', buttonIndex:19},
        	"indent": {name:'Increase Indent', openWith:'[indent] ', closeWith:'[/indent]', buttonIndex:20},
        	"youtube": {name:'YouTube'          
           	           ,popupPrompt: 'YouTube Link'
        	           ,popupButton: 'Insert Video'
        	           ,popupValue: function(url) {
									// first take out the params part
									var params = url.split('/').pop().split('?').pop();        
									// may be a multi-part url string, so try to take out
									// a v=xx code, otherwise whole thing is the code
									var code = params.split('&').shift().replace('v=','');		                                           
									if ( code.match(/^[A-Za-z0-9_\-]+$/) ) { return code; }
									return "";
								}
        	           ,openWith:'[youtube]{input}'
        	           ,closeWith:'[/youtube]'
        	           ,buttonIndex:21
        	          },
			"wowitem": {name:'WoW Item'
					   ,popupType: 'search-game-item'
					   ,game_id: 14
           	           ,popupPrompt: 'Search World of Warcraft items'
        	           ,popupButton: 'Insert'
        	           ,popupValue: function(url) {
							return url;
                        }
        	           ,openWith:'[wowitem]{input}'
        	           ,closeWith:'[/wowitem]'
        	           ,buttonIndex:22
        	          },
			"riftitem": {name:'Rift Item'
					   ,popupType: 'search-game-item'
					   ,game_id: 4910
           	           ,popupPrompt: 'Search Rift items'
        	           ,popupButton: 'Insert'
        	           ,popupValue: function(url) {
							return url;
                        }
        	           ,openWith:'[riftitem]{input}'
        	           ,closeWith:'[/riftitem]'
        	           ,buttonIndex:23
        	          },
			"torhead": {name:'Torhead Item'
				,popupPrompt: 'SWTOR item name'
				,popupButton: 'Insert'
				,popupValue: function(url) {
					url = jQuery.trim(url);
					return url;
				}
				,openWith:'[torhead]{input}'
				,closeWith:'[/torhead]'
				,buttonIndex:24
			},
        	"dcpu": {name:'DCPU Code', openWith:'[code=dcpu]', closeWith:'[/code]', buttonIndex:25},
			"teratome": {name:'Tera Tome Item'
				,popupPrompt: 'Tera Tome item name'
				,popupButton: 'Insert'
				,popupValue: function(url) {
					url = jQuery.trim(url);
					return url;
				}
				,openWith:'[teratome]{input}'
				,closeWith:'[/teratome]'
				,buttonIndex:27
			},		
			"d3item": {name:'Diablo 3 Item'
				,popupPrompt: 'Diablo 3 item name'
				,popupButton: 'Insert'
				,popupValue: function(url) {
					url = jQuery.trim(url);
					return url;
				}
				,openWith:'[d3item]{input}'
				,closeWith:'[/d3item]'
				,buttonIndex:26
			},
			"ffxivitem": {name:'FFXIV: A Realm Reborn Item'
				,popupPrompt: 'FFXIV item name'
				,popupButton: 'Insert'
				,popupValue: function(url) {
					url = jQuery.trim(url);
					return url;
				}
				,openWith:'[ffxivarr]{input}'
				,closeWith:'[/ffxivarr]'
				,buttonIndex:38
			},		
			"tesoitem": {name:'The Elder Scrolls Online Item'
				,popupPrompt: 'Elder Scrolls Online item name'
				,popupButton: 'Insert'
				,popupValue: function(url) {
					url = jQuery.trim(url);
					return url;
				}
				,openWith:'[teso]{input}'
				,closeWith:'[/teso]'
				,buttonIndex:39
			},			
			"table": {name:'Table'
				,popupPrompt: [ {label:'Rows', cls:'table-inputs rows', name:'rows', attrs:{maxlength:1}}, {label:'Cols', cls:'table-inputs cols', name: 'cols', attrs:{maxlength:1}} ]
				,popupConfig: {labelAlign:'left', labelWidth:50}
				,popupButton: 'Insert Table'
				,popupValue: function(data) { 					
					var table = '';
							
					data.rows = parseInt(data.rows,10);
					data.cols = parseInt(data.cols,10);
					if ( !data.rows ) { data.rows = 2; }
					if ( !data.cols ) { data.cols = 2; }
					
					for ( var r = 0; r < data.rows; r++ )
					{
						table += "[tr]\n";
						for ( var c = 0; c < data.cols; c++ )
						{
							table += (r == 0 && data.rows > 1 ? "[th]Title "+(c+1)+"[/th]" : "[td]Cell "+(r+1)+"-"+(c+1)+"[/td]") + "\n";							
						}
						table += "[/tr]\n";
					}
					
					return table;
				}
				,openWith:'[table]\n{input}'
				,closeWith:'[/table]'
				,buttonIndex:28
			},
        	//"wowitem": {name:'WOW Item', openWith:'[wow] ', closeWith:'[/wow]', buttonIndex:22}
            "warning": {
                name: 'Warning', 
                openWith: '[warning]', 
                closeWith: '[/warning]',
                buttonIndex: 36
            },
            "noparse": {
                name: 'No parse', 
                openWith: '[noparse]', 
                closeWith: '[/noparse]',
                buttonIndex: 37
            },
            "highlight": {
                name: 'Highlight', 
                popupContent: function() {
                    var html = '<div class="colors">';
                    var colors = [
                        'eeeeee', 'bebebe', '989898', '7a7a7a', '4e4e4e', '3e3e3e', '282828', '181818',
                        'f3b2b1', 'f3d9b1', 'f3efb1', 'caf3b1', 'b1dcf3', 'b1c6f3', 'ccb1f3', 'eeacf9',
                        'dd2423', 'dd9323', 'ddd123', '6cdd23', '239edd', '2360dd', '6e23dd', 'd113ee',
                        '761413', '764f13', '767013', '3a7613', '135476', '133376', '3b1376', '6f0a7f'
                    ];
                    for ( var i = 0; i < colors.length; i++ ) { 
                        html += '<div data-open="[highlight=#' + colors[i] + ']" data-close="[/highlight]" class="swatch target" style="background: #' + colors[i] + ';" <!-- --></div>'; 
                    }
                    return html + "</div>";
                },
                buttonIndex: 33
            },
            "attach": {
                name: 'Attach File', 
                beforeInsert: function(){
                    $('.m_forum .element-add-file input[type=file]').trigger('click');
                },
                buttonIndex: 8
            },
            "email": {
                name: 'Email', 
                openWith: '[email=]', 
                closeWith: '[/email]',
                buttonIndex: 30
            },
            "superscript": {
                name: 'Superscript', 
                openWith: '[sup]', 
                closeWith: '[/sup]',
                buttonIndex: 32
            },
            "subscript": {
                name: 'Subscript', 
                openWith: '[sub]', 
                closeWith: '[/sub]',
                buttonIndex: 34
            },
            "justify": {
                name: 'Justify', 
                openWith: '[justify]', 
                closeWith: '[/justify]',
                buttonIndex: 35
            },
            "line": {
                name: 'Line', 
                openWith: '[rule]', 
                buttonIndex: 29
            },
            "columns": {
                name: 'Columns', 
                openWith: '[columns]\nColumn text 1\n[nextcol]\nColumn text 2\n[nextcol]\nColumn text 3\n',
                closeWith: '[/columns]',
                buttonIndex: 31
            }
	    }
}