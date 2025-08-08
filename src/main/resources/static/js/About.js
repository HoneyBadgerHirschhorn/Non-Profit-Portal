document.addEventListener('DOMContentLoaded', function () {
    // Banner Slideshow Logic
    const images = document.querySelectorAll('.banner img');
    const leftArrow = document.querySelector('.arrow.left');
    const rightArrow = document.querySelector('.arrow.right');
    let currentIndex = 0;
    let interval = setInterval(nextSlide, 10000); // 10 seconds auto slide

    function showSlide(index) {
        images.forEach((img, i) => {
            img.classList.toggle('active', i === index);
        });
    }

    function nextSlide() {
        currentIndex = (currentIndex + 1) % images.length;
        showSlide(currentIndex);
    }

    function changeSlide(direction) {
        clearInterval(interval); // stop auto-slide when manually navigating
        currentIndex = (currentIndex + direction + images.length) % images.length;
        showSlide(currentIndex);
        interval = setInterval(nextSlide, 10000); // restart auto-slide
    }

    if (leftArrow) {
        leftArrow.addEventListener('click', () => changeSlide(-1));
    }
    if (rightArrow) {
        rightArrow.addEventListener('click', () => changeSlide(1));
    }
});
