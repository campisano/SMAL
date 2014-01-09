
﻿    jQuery.fx.interval = 80;
    
    (function ($) {
        $.fn.pBox = function (options) {
        var defaults = {
            icon: "/smal/scripts/pbox/img/alerta.png",
            title: "box title",
            width : "auto",
            height : "auto",
            hideOnOverlayClick: true,
            overlayShow: true,
            focusFirstInput: true,
            padding: 20,
            onClose : function() { return false; },
            onCreate: function() { return false; },
            onClick: function() { return false; }
        };
        var values = $.extend(defaults, options);
        if(this.length == 0)
        {
            values.onError();    
            return;
        }
        var isIframe = false;
        var identifier = this.attr('href');
        this.attr('href','#');
        var html = "";
        var maxW = $(window).width() - 100;
        var maxH = $(window).height() - 200;
        var w = (values.width != "max") ? values.width : maxW;
        var h =(values.height != "max") ? values.height : maxH;
        var overlay = null;
        
        var parent = $(identifier).parent();
        if(parent.attr('class') != 'box_content') {
            $(identifier).wrap("<div class='box_overlay'></div>").wrap("<div class='box_container'></div>").wrap("<div class='box_content'></div>");
            $(identifier).parent().before("<div class='box_header'><img src='" + values.icon + "' class='box_icon'><h1>" + values.title + "</h1><a class='anchor_close'><img src='/smal/scripts/pbox/img/fechar.png'></a></div>");
        }
        var container = $(identifier).parent().parent();
        var shadowAdded = false;
        /*container.draggable({
            scroll: false,
            cancel: ".box_content",
            containment: "parent",
            scroll: false,
            drag: function () {
                if (!shadowAdded) { 
                    $(this).addClass("draggable_box");
                    $(this).parent().addClass("none_overlay"); }
            },
            stop: function () {
                $(this).removeClass("draggable_box");
                $(this).parent().removeClass("none_overlay");
            }
        });*/
        overlay = $(identifier).parent().parent().parent();
        container.find(".anchor_close").bind('click',function() { 
            overlay.removeClass("selected").removeClass('active');
        });
        
        container.find(".box_content").css('padding',values.padding);
        eval(values.onCreate());
        var self = $(this);
        container.css({ 'minWidth': w, 'height': h });
        limitSize(container);
        this.bind("click", function () {
            var inputs = container.find("input, textarea, select");
            if (inputs.length > 0) {
            setTimeout(function () { $(inputs[0]).focus(); }, 200);
            }
            values.onClick(self);
            $(".box_overlay.active").removeClass('active');
            overlay.addClass("selected").addClass('active');
            container.css({'minWidth': w, 'height': h });
            var width = container.width();
            var height = container.height();
            var dW = $(window).width();
            var dH = $(window).height();
            
            container.css({ "left": ((dW/2) - width/2) + "px", 
                "top": /*((dH/2) - height/2)*/ "56" + "px"
            });
            limitSize(container);
        });
             
        $(document).keyup(function(e) {
            if (e.keyCode == 27) { 
                $(".box_overlay.selected.active").removeClass("selected").removeClass('active', function () { setTimeout(function () { $(".box_overlay.selected").addClass("active"); }, 100); });
                
            }
        });
    }
})(jQuery);

function createBox(title, content, options)
{
        var defaults = {
            icon: "/img/alerta.png",
            width : "auto",
            height : "auto",
        };
        var mainHtml = '';
        if(content.is(Object)) {
           mainHtml = content.html();
           content.remove();
        }
        else
        {
            mainHtml = content;
        }
        var values = $.extend(defaults, options);
        var header = "<div class='box_overlay' id='box_id'>" +
                        "<div class='box_container'>" +
                            "<div class='box_header'><img src='" + values.icon + "' class='box_icon'><h1>" + title + "</h1><a class='anchor_close'><img src='/img/fechar2.png'></a></div><div class='box_content'>";
        var footer = "</div></div></div>";
        $(header + mainHtml + footer).appendTo($("body"));
        //close click
        $("#box_id").fadeToggle("medium");
        var container = $("#box_id").find(".box_container");
        container.css('minWidth',values.width);
        container.css('minHeight',values.height);
        var width = container.width();
        var height = container.height();
        container.css('left', "-" + width/2 + "px");
        container.css('top', "-" +  height/2 + "px");
        $("#box_id").find(".anchor_close").click(function () {
            $(this).parent().parent().parent().fadeOut("slow",function() { $(this).remove();});
        });

}

function showHideBox(box) {
    var overlay = $(box).parent().parent().parent();
    var container = $(box).parent().parent();
    if(overlay.hasClass('selected')) { 
        overlay.removeClass('selected').removeClass('active');
    }
    else
    {
        $(".box_overlay.active").removeClass('active');
        overlay.addClass('selected').addClass('active');
        var inputs = container.find("input, textarea, select");
        if (inputs.length > 0) {
            setTimeout(function () { $(inputs[0]).focus(); }, 200);
        }
    }
    var width = container.width();
    var height = container.height();
    var dW = $(window).width();
    var dH = $(window).height();
    limitSize(container);
    container.css({ "left": ((dW/2) - width/2) + "px", 
        "top": /*((dH/2) - height/2)*/ "56" + "px"
    }); 
}

function limitSize(container) {
    if (container.height() >= $(window).height()) {
        container.find(".box_content").outerHeight($(window).height() - container.find(".box_header").outerHeight());
        container.css({ borderRadius: 0 });
    }
}