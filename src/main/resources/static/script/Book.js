function initSlider(sliderIdH,slideIdH,prevButtonH,nextButtonH,visibleSlideCount){
  const prevButton = document.getElementById(prevButtonH);
  const nextButton = document.getElementById(nextButtonH);
  const slider = document.querySelector(sliderIdH);
  const books = document.querySelectorAll(slideIdH);
  
  const firstSlide = document.querySelector(slideIdH);
  const slideWidth = firstSlide.offsetWidth;
  
  console.log(firstSlide)
  console.log(slideWidth)

  const sliderWidth = slideWidth * visibleSlideCount; 
  let currentIndex = 0;

  function movePrev() {
      if (currentIndex > 0) {
          currentIndex--;
          updateSliderPosition();
      }
  }

  function moveNext() {
      if (currentIndex < (books.length - visibleSlideCount)) {
          currentIndex++;
          updateSliderPosition();
      }
  }

  function updateSliderPosition() {
      const offset = -currentIndex * slideWidth;
      slider.style.transform = `translateX(${offset}px)`;
  }

  prevButton.addEventListener("click", movePrev);
  nextButton.addEventListener("click", moveNext);
};

document.addEventListener("DOMContentLoaded", function() {
  if (document.querySelector(".booksGrid")) {
      initSlider(".booksGrid",".book","prevButtonB","nextButtonB",4.5);
  }

  if (document.querySelector(".ReviewGrid")) {
      initSlider(".ReviewGrid",".Review","prevButtonR","nextButtonR",2.8);
  }

  if (document.querySelector(".semilunarBookSlider")) {
      initSlider(".semilunarBookSlider",".semilunarbook","prevBtn","nextBtn",4.2);
  }
});