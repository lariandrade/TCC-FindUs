(function ($) {
	
	"use strict";

	// Header Scrolling Set White Background
	scrollNavBar();

	// Window Resize Mobile Menu Fix
	mobileNav();


	// Scroll animation init
	window.sr = new scrollReveal();
	

	// Menu Dropdown Toggle
	if($('.menu-trigger').length){
		$(".menu-trigger").on('click', function() {	
			$(this).toggleClass('active');
			$('.header-area .nav').slideToggle(200);
		});
	}


	// Menu elevator animation
	$('a[href*=\\#]:not([href=\\#])').on('click', function() {
		if (location.pathname.replace(/^\//,'') == this.pathname.replace(/^\//,'') && location.hostname == this.hostname) {
			var target = $(this.hash);
			target = target.length ? target : $('[name=' + this.hash.slice(1) +']');
			if (target.length) {
				var width = $(window).width();
				if(width < 991) {
					$('.menu-trigger').removeClass('active');
					$('.header-area .nav').slideUp(200);	
				}				
				$('html,body').animate({
					scrollTop: (target.offset().top) - 30
				}, 700);
				return false;
			}
		}
	});


	// Blog cover image
	if($('.blog-post-thumb').length){
		$('.blog-post-thumb .img').imgfix();
	}


	// Home img
	if($('.home-img').length){
		$('.home-img').imgfix({
			scale: 1.1
		});
	}


	// Home img
	if($('.home-about-single').length){
		$('.home-about-single').imgfix({
			scale: 1.1
		});
	}


	// About img
	if($('.about-img').length){
		$('.about-img').imgfix({
			scale: 1.1
		});
	}


	// Home Banner img
	if($('.banner').length){
		$('.banner').imgfix();
	}


	// Testimonials img
	if($('.testimonials').length){
		$('.testimonials').imgfix();
	}


	// // Welcome area init
	if($('.testimonial-slider-one').length){
		var testimonialOne = $(".testimonial-slider-one");
		testimonialOne.owlCarousel({
			loop: true,
			nav: false,
			margin: 30,
			autoplay: true,
			autoplayTimeout: 4000,
			autoplayHoverPause: true,
			responsive: {
				0: {
					items: 1
				},
				600: {
					items: 1
				},
				1000: {
					items: 2
				}
			}
		});

		$(".arrows .prev").on('click', function() {
			testimonialOne.trigger('prev.owl.carousel');
		});

		$(".arrows .next").on('click', function() {
			testimonialOne.trigger('next.owl.carousel');
		});
	}


	// // Welcome area init
	if($('.testimonial-slider-two').length){
		var testimonialTwo = $(".testimonial-slider-two");
		testimonialTwo.owlCarousel({
			loop: false,
			nav: false,
			dot: true,
			margin: 30,
			autoplay: true,
			autoplayTimeout: 4000,
			autoplayHoverPause: true,
			responsive: {
				0: {
					items: 1
				},
				600: {
					items: 2
				},
				1000: {
					items: 3
				}
			}
		});

		$(".arrows .prev").on('click', function() {
			testimonialTwo.trigger('prev.owl.carousel');
		});

		$(".arrows .next").on('click', function() {
			testimonialTwo.trigger('next.owl.carousel');
		});
	}


	// Sidebar contact banner image
	if($('.sidebar .box').length) {
		$('.sidebar .box').imgfix();
	}	


	// Page loading animation
	$(window).on('load', function() {
		if($('.cover').length){
			$('.cover').parallax({
				imageSrc: $('.cover').data('image'),
				zIndex: '1'
			});
		}

		$("#loader").animate({
			'opacity': '0'
		}, 600, function(){
			setTimeout(function(){
				// Home Parallax
				if($('.parallax').length){
					$('.parallax').parallax({
						imageSrc: 'assets/images/photos/parallax/parallax.jpg',
						zIndex: '1'
					});
				}
				$("#loader").css("visibility", "hidden").fadeOut();
			}, 300);
		});
	});


	// Header Scrolling Set White Background
	$(window).on('scroll', function() {
		scrollNavBar();
	});


	// Window Resize Mobile Menu Fix
	$(window).on('resize', function() {
		mobileNav();
	});


	// Window Resize Mobile Menu Fix
	function mobileNav() {
		var width = $(window).width();
		$('.submenu').on('click', function() {
			if(width < 992) {
				$('.submenu ul').removeClass('active');
				$(this).find('ul').toggleClass('active');
			}
		});
	}


	// Navbar Scroll Set White Background Function
	function scrollNavBar() {
		var width = $(window).width();
		if(width > 991) {
			var scroll = $(window).scrollTop();
			if (scroll >= 30) {
				$(".header-area").addClass("header-sticky");
				if($('.header-area').hasClass('light-header')) {
					$(".header-area .dark-logo").css('display', 'none');
					$(".header-area .light-logo").css('display', 'block');
				}
			}else{
				$(".header-area").removeClass("header-sticky");
				if($('.header-area').hasClass('light-header')) {
					$(".header-area .dark-logo").css('display', 'block');
					$(".header-area .light-logo").css('display', 'none');
				}
			}
		}
	}


})(window.jQuery);