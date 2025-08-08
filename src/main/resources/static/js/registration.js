// Function to toggle visibility of Kid Info textarea
function toggleKidInfo() {
    const hasKids = document.getElementById("has_kids").value;
    const kidInfoSection = document.getElementById("kid_info_section");

    if (hasKids === "1") { // If "Yes" is selected
        kidInfoSection.style.display = "block"; // Show the Kid Info section
    } else {
        kidInfoSection.style.display = "none"; // Hide the Kid Info section
    }
}

document.addEventListener("DOMContentLoaded", function () {
    // console.log("🟢 DOM Loaded! JavaScript is running.");

    const form = document.getElementById("registrationForm");
    if (!form) {
        console.error("❌ ERROR: Form not found!");
        return;
    }
    document.getElementById("has_kids").addEventListener("change", toggleKidInfo);
    // Set initial visibility on load
    toggleKidInfo();

    console.log("🟢 Form found. Attaching submit event...");
    form.addEventListener("submit", handleSubmit);
});

async function handleSubmit(event) {

    // console.log("🟢 JavaScript file is loaded!"); // Debugging check



    event.preventDefault(); // Prevents form from submitting traditionally
    alert("🟢 handleSubmit called in JS!");

    const formData = new FormData(document.getElementById("registrationForm"));
    const data = {
        first_name: formData.get("first_name"),
        last_name: formData.get("last_name"),
        date_of_birth: formData.get("date_of_birth"),
        gender: formData.get("gender"),
        address: formData.get("address"),
        marital_status: formData.get("marital_status"),
        has_kids: formData.get("has_kids") === "1", // Convert to boolean
        kid_info: formData.get("kid_info"),
        member: formData.get("member") === "1", // Convert to boolean
        contact_phone: formData.get("contact_phone"),
        contact_email: formData.get("contact_email"),
        attending_since: formData.get("attending_since")
    };

    console.log("🔵 Sending JSON:", JSON.stringify(data)); // Debugging output before sending request

    try {
        const response = await fetch("http://localhost:8080/api/register", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(data)
        });

        console.log("🟢 Response Status:", response.status); // Debug response status

        const responseText = await response.text();
        console.log("🟢 Server Response:", responseText); // Debug server response

        if (response.ok) {
            alert("Registration successful in JS!");
        } else {
            console.error("❌ Server Error:", responseText);
            alert("There was an error with the registration.");
        }
    } catch (error) {
        console.error("❌ Fetch Error:", error);
        alert("There was an error with the registration.");
    }
    // Call toggleKidInfo when page loads to set initial visibility

}
