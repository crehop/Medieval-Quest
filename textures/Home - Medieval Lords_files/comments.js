$(document).ready(function(){

    // event delegation
    $('body').bind('click', function(event){
        var target = $(event.target);
        if (target.is('.m_system-dashboard .like-comment, .m_system-profile .like-comment, .m_plugin_comment .like-comment, .m_sitewall .like-comment')){

            // like/unlike a comment
            var likes = parseInt(target.next().children().html());            
            if (target.html() == 'Like'){
                target.html('Unlike');
                likes++;
                $.get(target.attr('href') + 'like-comment');
            } else {    // Unlike
                target.html('Like');
                likes--;
                $.get(target.attr('href') + 'unlike-comment');
            }
            
            if (likes > 0){
                target.next().show().children().html('+' + likes);
            } else {
                target.next().hide().children().html(0);
            }
            event.preventDefault();
        }
    });
});