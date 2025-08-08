

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