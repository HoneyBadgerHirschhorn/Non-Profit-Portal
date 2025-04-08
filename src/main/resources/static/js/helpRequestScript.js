// Add an event listener to the form submit event
document.getElementById("helpRequestForm").addEventListener("submit", function(event) {
    // Prevent the default form submission behavior
    event.preventDefault();

    // Get the form data
    const formData = new FormData(this);

    // Convert the form data to JSON
    const jsonData = Object.fromEntries(formData);

    // Send the JSON data to the Spring controller via a POST request
    fetch('http://localhost:8080/api/helpRequest', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(jsonData)
    })
        .then(response => response.json())
        .then(data => console.log(data))
        .catch(error => console.error(error));
});