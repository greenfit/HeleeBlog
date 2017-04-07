$(function(){
  var appendCatalog = function(){
    //根据内容生成菜单
    $(".blog-body").find("h2,h3").each(function(){
      var catalog = $(this)[0];
      var liHtml = '<li class="blog-catalog-' + catalog.localName + '"><a class="anchorLink" href="#' + catalog.id + '">' + catalog.innerText + '</a></li>';
      $(".catalog-content").append(liHtml);
    });
    $(".catalog").css("display", "block");
    $(".catalog").css("height", $(window).height() - 170);//调整菜单的高度

      
    $(window).scroll(function(e){
      //根据当前滚动来设置菜单位置
      var top = $("body").scrollTop();
      if(top > 400) {
          $(".catalog").addClass("catalog-fixed");
          $(".catalog").css("width", $(".right").width());
      } else {
          $(".catalog").removeClass("catalog-fixed"); 
          $(".catalog").css("width", "88%");
      }

      //获取当前查看的            
      var watch = $(".blog-body h2,h3").first();
      $(".blog-body h2,h3").each(function(){
          if($(this).offset().top > $(window).scrollTop() + 21) {
              return false;
          }
          watch = $(this);
      });

      //同步菜单中的
      var select = ".catalog-content li a[href='#" + watch[0].id + "']";
      if($(select).length == 0) return;
      $(select).parent().addClass("active").siblings().removeClass("active");
      //$(select).parent().prevAll(".blog-catalog-h2").first().addClass("active");           
      $(".sidebar-scrollbar").css("top", $(select).position().top);
      $(".catalog").scrollTop($(select).position().top - ($(".catalog").height() / 2));//保持在菜单的中间位置
    });
  }
  
  jQuery.fn.anchorAnimate = function(settings) {

    settings = jQuery.extend({
      speed : 1000
    }, settings); 
    
    return this.each(function(){
      $(this).click(function (event) {  
        event.preventDefault()
        var locationHref = window.location.href
        var elementClick = $(this).attr("href")

        var destination = $(elementClick).offset().top - 20;
        $("html:not(:animated),body:not(:animated)").animate({ scrollTop: destination}, settings.speed, function() {
          window.location.hash = elementClick
        });
          return false;
      })
    })
  }
  
  appendCatalog();
  $("a.anchorLink").anchorAnimate();
});

