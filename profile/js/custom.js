
$(window).load(function(){
    $('.preloader').delay(1000).fadeOut("slow");
});

$(function(){
    jQuery(document).ready(function() {
        var imgs = ["images/tm-bg-slide-1.jpg", "images/tm-bg-slide-2.jpg", "images/tm-bg-slide-3.jpg"]
		$('body').backstretch(imgs, {duration: 3200, fade: 1300});
	});
})

$(".card").hover(function(){
    $(this).css("box-shadow", "0px 0px 30px #00bcd4");
  }, function(){
    $(this).css("box-shadow", "0px 0px 0px #00bcd4");
});