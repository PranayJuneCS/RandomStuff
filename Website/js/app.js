$(document).ready(function() {
    if($(".splash").is(":visible")) {
        $(".next").css({"opacity":"0"});
    }

    $(".btn").click(function() {
        $(".splash").fadeOut("800", function() {
            $(".next").animate({"opacity":"1.0"},800);
        });
    });

    $(".nav > li").click(function() {
    	//deals with pills
    	var text = $(this).text();
    	$(".active").removeClass("active");
    	$(this).addClass("active");

    	//deals with dots
    	$(".active-dot").removeClass("active-dot");
    	var dots = $(".slider-dots").children();
    	for (var i = 0; i < dots.length; i++) {
    		if ($(dots[i]).hasClass(text)) {
    			$(dots[i]).addClass("active-dot");
    			break;
    		}
    	}

    	//deals with slides
    	$(".active-slide").fadeOut(600).removeClass("active-slide");
    	var slides = $(".slider").children();
    	for (var j = 0; j < slides.length; j++) {
    		if ($(slides[j]).hasClass(text)) {
    			$(slides[j]).fadeIn(600).addClass("active-slide");
    		}
    	}

    });

    $('.arrow-next').click(function() {
    	//slides
        var currentSlide = $('.active-slide');
        var nextSlide = currentSlide.next();
        if (nextSlide.length === 0) {
            nextSlide = $('.slide').first();
        }
        currentSlide.fadeOut(600);
        currentSlide.removeClass('active-slide');
        nextSlide.fadeIn(600);
        nextSlide.addClass('active-slide');
        
        //dots
        var currentDot = $('.active-dot');
        var nextDot = currentDot.next();
        if (nextDot.length === 0) {
            nextDot = $('.dot').first();
        }
        currentDot.removeClass('active-dot');
        nextDot.addClass('active-dot');

        //pills
        var currentPill = $('.active');
        var nextPill = currentPill.next();
        if (nextPill.length === 0) {
            nextPill = $('.pill').first();
        }
        currentPill.removeClass('active');
        nextPill.addClass('active');
    });
    
    $('.arrow-prev').click(function() {
    	//slides
        currentSlide = $('.active-slide');
        prevSlide = currentSlide.prev();
        if (prevSlide.length === 0) {
            prevSlide = $('.slide').last();
        }
        currentSlide.fadeOut(600).removeClass('active-slide');
        prevSlide.fadeIn(600).addClass('active-slide');
        
        //dots
        var currentDot = $('.active-dot');
        var prevDot = currentDot.prev();
        if (prevDot.length === 0) {
            prevDot = $('.dot').last();
        }
        currentDot.removeClass('active-dot');
        prevDot.addClass('active-dot');

        //pills
        var currentPill = $('.active');
        var prevPill = currentPill.prev();
        if (prevPill.length === 0) {
            prevPill = $('.pill').last();
        }
        currentPill.removeClass('active');
        prevPill.addClass('active');
    });
});