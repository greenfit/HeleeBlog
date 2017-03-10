
$(window).load(function(){
    $('.preloader').delay(1000).fadeOut("slow");
});

$(function(){
    jQuery(document).ready(function() {
		$('body').backstretch([
	 		 "images/tm-bg-slide-1.jpg", 
	 		 "images/tm-bg-slide-2.jpg",
			 "images/tm-bg-slide-3.jpg"
	 			], 	{duration: 3200, fade: 1300});
		});
})

$(".about").hover(function(){
    $(".about").css("box-shadow", "0px 0px 10px #222");
  }, function(){
    $(".about").css("box-shadow", "0px 0px 0px #333");
  });