// helpRequestScript.js
// Handles submission of the “Submit Help Request” form
document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById("helpRequestForm");

    form.addEventListener("submit", async function(event) {
        event.preventDefault();

        // 1) Serialize form data to a plain object
        const formData = new FormData(this);
        const jsonData = Object.fromEntries(formData);

        try {
            // 2) Fetch user by first and last name
            const userResponse = await fetch(`http://localhost:8080/api/users/byName?firstName=${jsonData.firstName}&lastName=${jsonData.lastName}`);
            if (!userResponse.ok) {
                alert('User not found. Please register first.');
                return;
            }
            const users = await userResponse.json();
            if (users.length === 0) {
                alert('User not found. Please register first.');
                return;
            }
            const userId = users[0].id;
            jsonData.userId = userId;


            // 3) Send POST to our Spring endpoint
            const response = await fetch('http://localhost:8080/api/help-requests', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(jsonData)
            });

            // 4) Always read text first (to capture HTML or error messages)
            const bodyText = await response.text();
            const contentType = response.headers.get('content-type') || '';

            // 5) If server returned an error status, log and show it
            if (!response.ok) {
                console.error("❌ Server error:", bodyText);
                alert(`Error submitting help request: ${response.status} ${response.statusText}`);
                return;
            }

            // 6) Parse as JSON only if the content-type is JSON
            let data;
            if (contentType.includes('application/json')) {
                data = JSON.parse(bodyText);
            } else {
                console.warn("⚠️ Expected JSON but got:", bodyText);
                data = { message: bodyText };
            }

            // 7) Success!
            console.log("✅ Help request submitted successfully:", data);
            alert("Help request submitted successfully!");
            this.reset();

        } catch (networkError) {
            console.error("❌ Network or parsing error:", networkError);
            alert("An unexpected error occurred. Please try again.");
        }
    });
});
