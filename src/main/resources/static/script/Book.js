document.addEventListener('DOMContentLoaded', function() {
    var sliders = document.querySelectorAll('.semilunarBookBlock');

    sliders.forEach(function(sliderContainer) {
        var slider = sliderContainer.querySelector('.slider');
        var slides = slider.querySelectorAll('.book');

        // Sprawdzenie, czy istnieją jakiekolwiek slajdy
        if (slides.length > 0) {
            var slideWidth = slides[0].offsetWidth;
            var isDragging = false;
            var startX;
            var scrollLeft;

            // Obserwator zdarzeń myszy/dotyku do przesuwania slajdów
            slider.addEventListener('mousedown', function(e) {
                isDragging = true;
                startX = e.pageX - slider.offsetLeft;
                scrollLeft = slider.scrollLeft;
            });

            slider.addEventListener('mouseleave', function() {
                isDragging = false;
            });

            slider.addEventListener('mouseup', function() {
                isDragging = false;
            });

            slider.addEventListener('mousemove', function(e) {
                if (!isDragging) return;
                e.preventDefault();
                const x = e.pageX - slider.offsetLeft;
                const walk = (x - startX) * 2;
                slider.scrollLeft = scrollLeft - walk;
            });


            slider.addEventListener('touchstart', function(e) {
                isDragging = true;
                startX = e.touches[0].pageX - slider.offsetLeft;
                scrollLeft = slider.scrollLeft;
            });

            slider.addEventListener('touchend', function() {
                isDragging = false;
            });

            slider.addEventListener('touchmove', function(e) {
                if (!isDragging) return;
                e.preventDefault();
                const x = e.touches[0].pageX - slider.offsetLeft;
                const walk = (x - startX) * 3;
                slider.scrollLeft = scrollLeft - walk;
            });
        } else {
            console.error('Nie znaleziono żadnych slajdów wewnątrz elementu .slider');
        }
    });
});