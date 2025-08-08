document.addEventListener("DOMContentLoaded", function () {
    const category = "Activities";

    fetch(`/api/posts/category/${encodeURIComponent(category)}`)
        .then(response => response.json())
        .then(data => displayPostsAndReplies(data))
        .catch(error => console.error("Failed to load posts:", error));
});

function displayPostsAndReplies(posts) {
    const container = document.getElementById("results");
    container.innerHTML = "";

    posts.forEach(post => {
        const postDiv = document.createElement("div");
        postDiv.classList.add("post");

        let repliesHTML = "";
        if (post.replies && post.replies.length > 0) {
            repliesHTML = "<ul>" + post.replies.map(reply =>
                `<li>${reply.message} - <i>${reply.authorName}</i></li>`
            ).join("") + "</ul>";
        }

        postDiv.innerHTML = `
                <h3>${post.title}</h3>
                <p>${post.content}</p>
                <small>Posted by ${post.authorName}</small>
                <div class="replies">
                    <h4>Replies:</h4>
                    ${repliesHTML || "<p>No replies yet.</p>"}
                </div>
            `;

        container.appendChild(postDiv);
    });
}