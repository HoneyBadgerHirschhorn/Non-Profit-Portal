// Banner Slideshow Logic
const images = document.querySelectorAll('.banner img');
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

// Calendar Initialization
document.addEventListener('DOMContentLoaded', function () {
    const calendarEl = document.getElementById('calendar');

    const calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        editable: true,          // enables drag/drop and resize
        selectable: true,        // enables selecting date ranges
        selectHelper: true,      // visual cue while selecting
        events: [],              // start with no events

        // On selecting a date range, prompt to add an event
        select: function(info) {
            const title = prompt("Enter Event Title:");
            if (title) {
                calendar.addEvent({
                    title: title,
                    start: info.startStr,
                    end: info.endStr,
                    allDay: info.allDay
                });
            }
            calendar.unselect();
        },

        // Clicking an event lets the user remove it
        eventClick: function(info) {
            if (confirm(`Delete event "${info.event.title}"?`)) {
                info.event.remove();
            }
        }
    });

    calendar.render();
});