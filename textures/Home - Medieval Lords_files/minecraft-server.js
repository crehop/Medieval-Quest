$(document).ready(function(){

    // players online - player tooltip and character link
    $('.m_minecraftserverplayersonline .players-container').bind('mouseover', function(event){
        var target = $(event.target);
        if (target.closest('.player').length){
            target.closest('.player').children('.tooltip-player').show();
        }
    }).bind('mouseout', function(event){
        var target = $(event.target);
        if (target.closest('.player').length){
            target.closest('.player').children('.tooltip-player').hide();
        }
    }).bind('click', function(event){
        var target = $(event.target);
        if (target.closest('.character').length){
            location.href = target.closest('.character').attr('data-url');
        }
    });

    // players online - pagination
    $('.m_minecraftserverplayersonline .pagination').bind('click', function(event){
        var url = $(this).attr('data-url') + (parseInt($(this).attr('data-page')) + 1);
        var button = $(this);
        var loading = $(this).prev();
        var container = $(this).prevAll('.players-container');
        var pagination = $(this).parents('.players').prev();
        var pagination_current = pagination.find('.pagination-current');
        var pagination_total = pagination.find('.pagination-total');

        loading.show();
        $.get(url, function(data){
            loading.hide();
            container.append(data);

            button.attr('data-page', parseInt(button.attr('data-page')) + 1);
            pagination_current.html(container.children('.player').length);
            if (parseInt(pagination_current.html()) >= parseInt(pagination_total.html())){
                button.hide();
            }
        });
    });


    // top players online - link
    $('.m_minecraftservertopplayersonline .character').bind('click', function(){
        location.href = $(this).attr('data-url');
    });
	
	var positionChartLegends = function(self) {
		var data = self.data("_jqs_mhandler");
		if ( typeof data !== 'undefined' ) {
			// position the legends by placing them centered under the data point they refer to		
			var legend_container = self.parent().find('.legend');		
			var vertices = data.splist[0].vertices;
			var vertice_offset = self.attr('data-legendstep');
			if ( typeof vertice_offset === 'undefined' ) {  
				vertice_offset = [vertices.length / (legend_container.children().length-1)]; 
			} else {
				vertice_offset = vertice_offset.split(',');
			}
			
			var vertice_index = 0;
			
			legend_container.children().each( function() { 				
					// get the x-offset of the vertex, this is the center position of the legend, with the following exceptions:
					//   1. first legend - left align to first vertex
					//	 2. end legend - right aligned to the last vertex				
					
					var vindex = vertice_index;
					var corner = 'center';
					if ( $(this).is(':first-child') ) {
						corner = 'left';
						$(this).removeClass('align-center').addClass('align-left');
					} else if ( $(this).is(':last-child') ) {
						corner = 'right';
						vindex = vertices.length-1;		// use last point, the actual computed vertice_index steps one past last element, so adjust
						$(this).removeClass('align-center').addClass('align-right');
					} 
					
					$(this).position({of: legend_container, my:corner + ' center', at:'left+'+vertices[vindex][0]+' center'});	
					vertice_index += parseInt(vertice_offset.length == 1 ? vertice_offset[0] : vertice_offset.shift(),10);		// we show a legend of every n vertices
				});		
		}		
	}


    // players online graph
    $('.m_minecraftserverplayersgraph .graph').each(function(){
		var self = $(this);
		var players_graph_data = $.parseJSON(self.attr('data-data'));
		if(!players_graph_data.length) return true;

		$(this).sparkline('html', {
			lineColor: self.attr('data-lineColor'),
			fillColor: self.attr('data-fillColor'),
			lineWidth: 1.8,
			spotColor: false,
			minSpotColor: false,
			maxSpotColor: false,
			chartRangeMin: 0,
			height: '36px',
            width: '100%',
			tooltipFormatter: function(sparkline, options, fields){
				var elem = players_graph_data[fields.x];
				return elem.date + '<br>' + elem.players_online + ' online players';
			}
		}).css('visibility', 'visible');
		
		positionChartLegends(self);
	});


    // server response graph
    $('.m_minecraftserverresponsegraph .graph').each(function(){
		var self = $(this);
		var response_graph_data = $.parseJSON(self.attr('data-data'));
		if(!response_graph_data.length) return true;

		$(this).sparkline('html', {
			lineColor: self.attr('data-lineColor'),
			fillColor: self.attr('data-fillColor'),
			lineWidth: 1.8,
			spotColor: false,
			minSpotColor: false,
			maxSpotColor: false,
			chartRangeMin: 0,
			height: '36px',
            width: '100%',
			tooltipFormatter: function(sparkline, options, fields){
				var elem = response_graph_data[fields.x];
				return elem.date + '<br>' + elem.response_time + ' ms (response time)';
			}
		}).css('visibility', 'visible');
		
		positionChartLegends(self);
	});

    // tps graph
    $('.m_minecraftservertpsgraph .graph').each(function(){
		var self = $(this);
		var players_graph_data = $.parseJSON(self.attr('data-data'));
		if(!players_graph_data.length) return true;

		$(this).sparkline('html', {
			lineColor: self.attr('data-lineColor'),
			fillColor: self.attr('data-fillColor'),
			lineWidth: 1.8,
			spotColor: false,
			minSpotColor: false,
			maxSpotColor: false,
			chartRangeMin: self.attr('data-chartrangemin'),
			height: '36px',
            width: '100%',
			tooltipFormatter: function(sparkline, options, fields){
				var elem = players_graph_data[fields.x];
				return elem.date + '<br>' + elem.tps + ' ticks per second';
			}
		}).css('visibility', 'visible');
		
		positionChartLegends(self);
	});	

    // minecraft votinv votes per hour graph
    $('.m_minecraftvoting .graph').each(function(){
		var self = $(this);
		var players_graph_data = $.parseJSON(self.attr('data-data'));
		if(!players_graph_data.length) return true;

		$(this).sparkline('html', {
			lineColor: self.attr('data-lineColor'),
			fillColor: self.attr('data-fillColor'),
			lineWidth: 1.8,
			spotColor: false,
			minSpotColor: false,
			maxSpotColor: false,
			chartRangeMin: 0,
			height: '36px',
            width: '100%',
			tooltipFormatter: function(sparkline, options, fields){
				var elem = players_graph_data[fields.x];
				return elem.date + '<br>' + elem.vote + ' votes per hour';
			}
		}).css('visibility', 'visible');
		
		positionChartLegends(self);
	});	
	
	
    // shop activity graph
    $('.m_shopactivity .graph').each(function(){
		var self = $(this);
		var shop_graph_data = $.parseJSON(self.attr('data-data'));
		if(!shop_graph_data.length) return true;

		$(this).sparkline('html', {
			lineColor: self.attr('data-lineColor'),
			fillColor: self.attr('data-fillColor'),
			lineWidth: 1.8,
			spotColor: false,
			minSpotColor: false,
			maxSpotColor: false,
			chartRangeMin: 0,
			height: '36px',
            width: '100%',
			tooltipFormatter: function(sparkline, options, fields){
				var elem = shop_graph_data[fields.x];
				return elem.date + '<br>' + elem.total_price_string + ' donated';
			}
		}).css('visibility', 'visible');
	});

	$('.m_minecraftvoting .voting-section.show_minimized .element_smalltitle,' +
	  '.m_minecraftvoting .voting-section.show_expanded .element_smalltitle').click(function(e) {
		$(this).closest('.voting-section').toggleClass('show_expanded').toggleClass('show_minimized');
		$.sparkline_display_visible();
	});

	$('.m_minecraftvoting .vote-link').click(function() {
		/*$(this).siblings('.voted-pending').show();
		$(this).hide();*/

		// Open vote site in iframe @todo
	});

	$('.m_minecraftvoting div.pagination a.arrow-left').click(function(e) {
		var pagination = $(this).parent();
		var page_el = $(this).siblings('span').children('.current-page');
		var current_page = parseInt(page_el.text(), 10);
		var items_per_page = parseInt(pagination.attr('data-pagination'), 10);
		var pages = parseInt(pagination.attr('data-pages'), 10);
		var list_items = pagination.prev().children();

		if(current_page > 1) {
			page_el.text(current_page-1);
			list_items.hide();
			var start = (current_page-2)*items_per_page;

			for(var i=start; i<start+items_per_page; i++) {
				if(list_items[i] != undefined) {
					$(list_items[i]).show();
				}
			}
		}
	});
	$('.m_minecraftvoting div.pagination a.arrow-right').click(function(e) {
		var pagination = $(this).parent();
		var page_el = $(this).siblings('span').children('.current-page');
		var current_page = parseInt(page_el.text(), 10);
		var items_per_page = parseInt(pagination.attr('data-pagination'), 10);
		var pages = parseInt(pagination.attr('data-pages'), 10);
		var list_items = pagination.prev().children();

		if(current_page < pages) {
			page_el.text(current_page+1);
			list_items.hide();
			var start = current_page*items_per_page;

			for(var i=start; i<start+items_per_page; i++) {
				if(list_items[i] != undefined) {
					$(list_items[i]).show();
				}
			}
		}
	});

	if(typeof Enjin_Messaging != 'undefined')
	$(Enjin_Messaging).bind('onMessageMinecraftVotifierVote', function(evt, message) {
		$('.m_minecraftvoting').each(function() {
			if($(this).attr('data-server_id') == message.data.server_id) {
				var server_list = $('.server-list[data-list_key=' + message.data.list_key + ']');
				server_list.find('.vote-link, .voted-pending').hide();
				server_list.find('.voted-check').show();
			}
		});
	});

	$('.m_minecraftserverstatus_new .minimize-bar').click(function(e) {
		if($(this).parent().hasClass('minimized')) {
			$(this).text('Minimize..').parent().removeClass('minimized');
		}
		else {
			$(this).text('View Online Players..').parent().addClass('minimized');
		}
	});

	$('.m_minecraftserverstatus_new .show-all').click(function(e) {
		var self = $(this);
		self.hide();
		$.get($(this).parent().attr('data-url'), function(result) {
			self.parent().html(result);
		});
	});

	$('.m_minecraftserverstatus_new .status').each(function(e) {
		if($(this).closest('.m_minecraftserverstatus_new').width() <= 300) {
			$(this).appendTo($(this).parent());
			$(this).addClass('lower-mode');
		}
	});
});
