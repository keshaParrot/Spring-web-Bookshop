let position = 0;
const slides = document.querySelectorAll('.slide');

function slideNext() {
    position = (position + 1) % slides.length;
    updateSlidePosition();
}

function slidePrev() {
    position = (position - 1 + slides.length) % slides.length;
    updateSlidePosition();
}

function updateSlidePosition() {
    const slideWidth = slides[position].offsetWidth;
    const newPosition = -slideWidth * position;
    document.querySelector('.slides').style.transform = `translateX(${newPosition}px)`;
}

document.getElementById('nextBtn').addEventListener('click', slideNext);
document.getElementById('prevBtn').addEventListener('click', slidePrev);