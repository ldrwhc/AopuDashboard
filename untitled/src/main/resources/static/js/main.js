/*

  Template: Forge Ecommerce Responsive Bootstrap Template
  Author: author name
  Version: 1
  Design and Developed by: Khairul Basher Arif + Ashim Kumar
  NOTE: If you have any note put here. 

*/
/*================================================
[  Table of contents  ]
================================================
  1. Featured Carousel active
  2. Main Slider
  3. Countdown
  4. Best Saller Carousel Active
  5. Blog Carousel Active
  6. Testimonial List Active
  7. Brand Carousel Active
  8. Test Popup Link
  9. price-slider active
  10. Input Plus Minus Button
  11. venobox
  12. jQuery MeanMenu
  13. wow js active
  14.  Payment Accordion
======================================
[ End table content ]
======================================*/


(function($) {
  "use strict";

  /*----------------------------
  1. Featured Carousel active
  ------------------------------ */    
    $(".featured-carousel").owlCarousel({
        autoPlay: false, 
        slideSpeed:2000,
        pagination:false,
        navigation:true,    
        items : 5,
        /* transitionStyle : "fade", */    /* [This code for animation ] */
        navigationText:["<i class='fa fa-angle-left'></i>","<i class='fa fa-angle-right'></i>"],
        itemsDesktop : [1199,4],
        itemsDesktopSmall : [991,3],
        itemsTablet: [767,2],
        itemsMobile: [479,1]
    }); 

  /*----------------------------
  2. Main Slider
  ------------------------------*/
    $('#mainSlider').nivoSlider({
      effect: 'boxRainGrowReverse',
      slices: 15,
      boxCols: 8,
      boxRows: 4,
      animSpeed: 500,
      pauseTime: 5000,
      startSlide: 0,
      directionNav: true,
      controlNavThumbs: true,  
      prevText: 'Pr <br /> ev',
      nextText: 'Ne <br /> xt',
      pauseOnHover: true,
      manualAdvance: true
     }); 

  /*---------------------
  3. Countdown
  --------------------- */
    $('[data-countdown]').each(function() {
      var $this = $(this), finalDate = $(this).data('countdown');
      $this.countdown(finalDate, function(event) {
      $this.html(event.strftime('<span class="cdown days"><span class="time-count">%-D</span> <p>Days</p></span> <span class="cdown hour"><span class="time-count">%-H</span> <p>Hour</p></span> <span class="cdown minutes"><span class="time-count">%M</span> <p>Min</p></span> <span class="cdown second"> <span><span class="time-count">%S</span> <p>Sec</p></span>'));
      });
    }); 
    
  /*----------------------------
  4. Best Saller Carousel Active
  ------------------------------ */    
    $(".best-seller-carousel").owlCarousel({
      autoPlay: false, 
      slideSpeed:2000,
      pagination:false,
      navigation:true,    
      items : 2,
      /* transitionStyle : "fade", */    /* [This code for animation ] */
      navigationText:["<i class='fa fa-angle-left'></i>","<i class='fa fa-angle-right'></i>"],
      itemsDesktop : [1199,2],
      itemsDesktopSmall : [991,2],
      itemsTablet: [767,2],
      itemsMobile: [479,1]
    });

  /*----------------------------
  5. Blog Carousel Active
  ------------------------------ */    
    $(".blog-carousel").owlCarousel({
      autoPlay: false, 
      slideSpeed:2000,
      pagination:false,
      navigation:true,    
      items : 2,
      /* transitionStyle : "fade", */    /* [This code for animation ] */
      navigationText:["<i class='fa fa-angle-left'></i>","<i class='fa fa-angle-right'></i>"],
      itemsDesktop : [1199,2],
      itemsDesktopSmall : [991,2],
      itemsTablet: [767,2],
      itemsMobile: [479,1]
    });

  /*----------------------------
  6. Testimonial List Active
  ------------------------------ */    
    $(".testimonial-list").owlCarousel({
      autoPlay: false, 
      slideSpeed:2000,
      pagination:false,
      navigation:true,    
      items : 1,
      /* transitionStyle : "fade", */    /* [This code for animation ] */
      navigationText:["<i class='fa fa-angle-left'></i>","<i class='fa fa-angle-right'></i>"],
      itemsDesktop : [1199,1],
      itemsDesktopSmall : [991,1],
      itemsTablet: [767,1],
      itemsMobile: [479,1]
    }); 

  /*----------------------------
  7. Brand Carousel Active
  ------------------------------ */    
    $(".brand-carousel").owlCarousel({
      autoPlay: false, 
      slideSpeed:2000,
      pagination:false,
      navigation:false,   
      items : 5,
      itemsDesktop : [1199,4],
      itemsDesktopSmall : [991,4],
      itemsTablet: [767,3],
      itemsMobile: [479,1]
    }); 

  /*----------------------------
  8. Test Popup Link
  ------------------------------ */ 
    $('.test-popup-link').magnificPopup({
      type: 'image'
      // other options
    });

  /*----------------------------
  9. price-slider active
  ------------------------------ */  
    $( "#slider-range" ).slider({
     range: true,
     min: 0,
     max: 500,
     values: [ 25, 325 ],
     slide: function( event, ui ) {
    $( "#amount" ).val( "$" + ui.values[ 0 ] + "       $" + ui.values[ 1 ] );
     }
    });
    $( "#amount" ).val( "$" + $( "#slider-range" ).slider( "values", 0 ) +
     "       $" + $( "#slider-range" ).slider( "values", 1 ) ); 

     
  /*----------------------------
  10. Input Plus Minus Button
  ------------------------------ */ 
    $(".cart-plus-minus").append('<div class="dec qtybutton">-</div><div class="inc qtybutton">+</div>');
    $(".qtybutton").on("click", function() {
        var $button = $(this);
        var oldValue = $button.parent().find("input").val();
        if ($button.text() === "+") {
            var newVal = parseFloat(oldValue) + 1;
        } else {
            // Don't allow decrementing below zero
            if (oldValue > 0) {
                var newVal = parseFloat(oldValue) - 1;
            } else {
                newVal = 0;
            }
        }
        $button.parent().find("input").val(newVal);
    }); 

  /*---------------------
  11. venobox
  --------------------- */
    $('.venobox').venobox();

  /*-------------------------------------------
  12. jQuery MeanMenu
  --------------------------------------------- */
    jQuery('nav#dropdown').meanmenu();

  /*-------------------------------------------
  13. wow js active
  --------------------------------------------- */
    new WOW().init();

  /* ---------------------------------
  14.  Payment Accordion
  * ---------------------------------*/ 
    $(".payment-accordion").collapse({
      accordion:true,
      open: function() {
      this.slideDown(550);
      },
      close: function() {
      this.slideUp(550);
      }   
    });
    
  /*-------------------------------------------
  15. scrollUp jquery active
  --------------------------------------------- */
    $.scrollUp({
        scrollText: '<i class="fa fa-angle-up"></i>',
        easingType: 'linear',
        scrollSpeed: 900,
        animation: 'fade'
    });

  /*************************
  16. tooltip
  *************************/
    $('[data-toggle="tooltip"]').tooltip();

})(jQuery);