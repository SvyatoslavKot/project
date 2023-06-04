$(".wrapper__slider-treatment").slick({
  slidesToShow: 3,
  slidesToScroll: 3,
  dots: false,
  arrows: true,
  adaptiveHeight: true,
  prevArrow: ".wrapper__arrows.left",
  nextArrow: ".wrapper__arrows.right",
  responsive: [
    {
      breakpoint: 1025,
      settings: {
        slidesToShow: 2,
        slidesToScroll: 2,
      },
    },
    {
      breakpoint: 578,
      settings: {
        slidesToShow: 1,
        slidesToScroll: 1,
      },
    },
  ],
});
$(".wrapper__slider-testi").slick({
  slidesToShow: 1,
  slidesToScroll: 1,
  vertical: true,
  verticalSwiping: true,
  dots: false,
  arrows: true,
  adaptiveHeight: true,
  adaptiveHeight: true,
  prevArrow: ".wrapper__arrows-ver.top",
  nextArrow: ".wrapper__arrows-ver.bottom",
});

// $(".closer__search").click(function () {
//   $(".wrapper__search").toggleClass("active");
// });

$("a[href^='#click-']").on("click", function (e) {
  e.preventDefault();
  $("html, body").animate(
    {
      scrollTop: $($(this).attr("href")).offset().top,
    },
    1000
  );
});
