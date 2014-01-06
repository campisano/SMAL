
﻿    jQuery.fx.interval = 80;
    
    (function ($) {
    $.fn.pBox = function (options) {
        var defaults = {
            icon: "/pbox/img/alerta.png",
            title: "box title",
            width : "auto",
            height : "auto",
            hideOnOverlayClick: true,
            overlayShow: true,
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
        
        if(identifier[0] != '.' && identifier[0] != '#') {
            var newID = identifier.replace(/[^a-zA-Z 0-9]+/g,'');
            html += "<iframe src='" + identifier + "?w="+ maxW + "&h=" + maxH + "' width='" + w + "' height='" + h + "' frameborder='0' id='"+ newID + "_iframe'></iframe>";
            $(html).appendTo("body").wrap("<div class='box_overlay' id='" + newID + "'></div>").wrap("<div class='box_container'></div>").wrap("<div class='box_content'></div>").parent().before("<div class='box_header'><img src='" + values.icon + "' class='box_icon'><h1>" + values.title + "</h1><a class='anchor_close'><img src='/pbox/img/fechar.png'></a></div>");
            var container = $("#" + newID).find(".box_container");
            container.find(".anchor_close").click(function() { 
                $(this).parent().parent().parent().fadeOut("fast",function() { eval(values.onClose()); }).removeClass("selected");
            });
            eval(values.onCreate());
            identifier = "#" + newID + "_iframe";
        }
        else
        {
            var parent = $(identifier).parent();
            if(parent.attr('class') != 'box_content') {
                $(identifier).wrap("<div class='box_overlay'></div>").wrap("<div class='box_container'></div>").wrap("<div class='box_content'></div>");
                $(identifier).parent().before("<div class='box_header'><img src='" + values.icon + "' class='box_icon'><h1>" + values.title + "</h1><a class='anchor_close'><img src='/pbox/img/fechar.png'></a></div>");
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
                overlay./*fadeOut("slow",function() { eval(values.onClose()); }).*/removeClass("selected");
            });
        }
        container.find(".box_content").css('padding',values.padding);
        eval(values.onCreate());
        var self = $(this);
        this.bind("click",function() {
            
            values.onClick(self);
            overlay/*.fadeIn("fast")*/.addClass("selected");
            container.css({'minWidth': w, 'minHeight': h });
            var width = container.width();
            var height = container.height();
            var dW = $(window).width();
            var dH = $(window).height();
            
            container.css({ "left": ((dW/2) - width/2) + "px", 
                "top": ((dH/2) - height/2) + "px"
            });
        });
        $(document).keyup(function(e) {
            if (e.keyCode == 27) { 
                $(".box_overlay.selected").removeClass("selected");
            }
        });

        var lW = 0;
        var lH = 0;
        setInterval(function() { 
            /*var width = container.width();
            var height = container.height();
            if(lW != width || lH != height) {
                lW = width; lH = height;
                container.css({ "marginLeft": "-" + Math.ceil(width/2) + "px", 
                    "marginTop": "-" + Math.ceil(height/2) + "px" 
                });
            }*/
        }, 500);

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
    //overlay.fadeToggle("slow");
    if(overlay.hasClass('selected')) { 
        overlay.removeClass('selected');
    }
    else
    {
        overlay.addClass('selected');
    }
    var container = $(box).parent().parent();
    var width = container.width();
    var height = container.height();
    var dW = $(window).width();
    var dH = $(window).height();
            
    container.css({ "left": ((dW/2) - width/2) + "px", 
        "top": ((dH/2) - height/2) + "px"
    }); 
}