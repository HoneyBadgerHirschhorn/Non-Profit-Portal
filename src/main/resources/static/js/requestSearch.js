// src/main/resources/static/js/requestSearch.js
// Minimal update: keeps your original logic and endpoints, just adds a
// "Respond to this request" form per help request + submit handler.

document.addEventListener("DOMContentLoaded", () => {
  const params = new URLSearchParams(window.location.search);
  const categoryName = params.get("name");

  document.getElementById("page-title").textContent = `Help Requests: ${categoryName}`;
  const container = document.getElementById("requests");

  fetch(`/api/help-requests/category/${encodeURIComponent(categoryName)}`)
      .then(res => res.ok ? res.json() : Promise.reject("Failed to fetch help requests."))
      .then(data => {
        if (data.length === 0) {
          container.innerHTML = `<p>No help requests found for "${categoryName}".</p>`;
          return;
        }

        data.forEach(req => {
          const section = document.createElement("div");
          section.className = "post-container";
          section.innerHTML = `
          <h2>${req.title}</h2>
          <p>${req.content}</p>
          <p><strong>Requested by:</strong> ${req.firstName} ${req.lastName}</p> 

          <div class="create-post">
            <h3>Respond to this request</h3>
            <form class="create-post-form" data-help-request-id="${req.id}">
              <input name="firstName" placeholder="Your first name" required />
              <input name="lastName" placeholder="Your last name" required />
              <input name="title" placeholder="Response title" required />
              <textarea name="content" placeholder="Describe how you can help" required></textarea>
              <button type="submit">Post Response</button>
            </form>
          </div>

          <div class="posts"></div>
        `;
          container.appendChild(section);

          const postContainer = section.querySelector(".posts");
          fetch(`/api/posts/help-request/${req.id}`)
              .then(res => res.json())
              .then(posts => {
                posts.forEach(post => {
                  const postDiv = document.createElement("div");
                  postDiv.className = "post-box";
                  postDiv.innerHTML = `
                <h3>${post.title}</h3>
                <p>${post.content}</p>
                <p><strong>By:</strong> ${post.firstName} ${post.lastName}</p>
                <button class="toggle-replies">Show Replies</button>
                <div class="replies" data-post-id="${post.postId}" style="display: none;"></div>
                <form class="reply-form" data-post-id="${post.postId}">
                  <input name="firstName" placeholder="First name" required />
                  <input name="lastName" placeholder="Last name" required />
                  <textarea name="content" placeholder="Reply..." required></textarea>
                  <button type="submit">Submit Reply</button>
                </form>
              `;
                  postContainer.appendChild(postDiv);

                  const repliesContainer = postDiv.querySelector(".replies");
                  const toggleRepliesBtn = postDiv.querySelector(".toggle-replies");

                  toggleRepliesBtn.addEventListener("click", async () => {
                    if (repliesContainer.style.display === "none") {
                      repliesContainer.style.display = "block";
                      if (!repliesContainer.hasChildNodes()) {
                        await loadReplies(post.postId, repliesContainer);
                      }
                      toggleRepliesBtn.textContent = "Hide Replies";
                    } else {
                      repliesContainer.style.display = "none";
                      toggleRepliesBtn.textContent = "Show Replies";
                    }
                  });
                });
              });
        });

        // ONE event listener that handles both creating posts and replying
        document.body.addEventListener("submit", async e => {
          // 1) Create a new post/response to a help request
          if (e.target.classList.contains("create-post-form")) {
            e.preventDefault();
            const form = e.target;
            const helpRequestId = parseInt(form.dataset.helpRequestId);

            const body = {
              firstName: form.firstName.value,
              lastName: form.lastName.value,
              title: form.title.value,
              content: form.content.value,
              // Backend expects the category name on PostDto → PostMapper resolves category_id
              category: categoryName
            };

            const res = await fetch(`/api/posts/help-request/${helpRequestId}`, {
              method: "POST",
              headers: { "Content-Type": "application/json" },
              body: JSON.stringify(body)
            });

            if (res.ok) {
              form.reset();
              const postsContainer = form.closest(".post-container").querySelector(".posts");
              postsContainer.innerHTML = "";
              const posts = await (await fetch(`/api/posts/help-request/${helpRequestId}`)).json();

              posts.forEach(post => {
                const postDiv = document.createElement("div");
                postDiv.className = "post-box";
                postDiv.innerHTML = `
                <h3>${post.title}</h3>
                <p>${post.content}</p>
                <p><strong>By:</strong> ${post.firstName} ${post.lastName}</p>
                <button class="toggle-replies">Show Replies</button>
                <div class="replies" data-post-id="${post.postId}" style="display: none;"></div>
                <form class="reply-form" data-post-id="${post.postId}">
                  <input name="firstName" placeholder="First name" required />
                  <input name="lastName" placeholder="Last name" required />
                  <textarea name="content" placeholder="Reply..." required></textarea>
                  <button type="submit">Submit Reply</button>
                </form>
              `;
                postsContainer.appendChild(postDiv);
              });
            } else {
              alert("Failed to create response.");
            }
            return; // prevent falling through
          }

          // 2) Create a reply to a specific post
          if (!e.target.classList.contains("reply-form")) return;

          e.preventDefault();
          const form = e.target;
          const postId = parseInt(form.dataset.postId);
          const parentInput = form.querySelector('input[name="parentReplyId"]');

          const body = {
            postId,
            firstName: form.firstName.value,
            lastName: form.lastName.value,
            content: form.content.value
          };

          if (parentInput) {
            body.parentReplyId = parseInt(parentInput.value);
          }

          const res = await fetch("/api/replies", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(body)
          });

          if (res.ok) {
            form.reset();
            const container = form.closest(".post-box").querySelector(".replies");
            container.innerHTML = "";
            await loadReplies(postId, container);
            container.style.display = "block";
          } else {
            alert("Failed to post reply.");
          }
        });
      });

  async function loadReplies(postId, container) {
    const res = await fetch(`/api/replies/post/${postId}`);
    const replies = await res.json();
    const tree = buildReplyTree(replies);
    container.innerHTML = renderReplies(tree, postId);
  }

  function buildReplyTree(replies) {
    const map = {};
    const roots = [];

    replies.forEach(r => {
      map[r.replyId] = { ...r, children: [] };
    });

    replies.forEach(r => {
      if (r.parentReplyId) {
        map[r.parentReplyId]?.children.push(map[r.replyId]);
      } else {
        roots.push(map[r.replyId]);
      }
    });

    return roots;
  }

  function renderReplies(replies, postId, depth = 0) {
    return replies.map(r => `
      <div class="reply-box" style="margin-left: ${depth * 2}em;">
        <p><strong>${r.firstName} ${r.lastName}</strong></p>
        <p>${r.content}</p>
        <button onclick="showReplyForm(${r.replyId}, ${postId})">Reply</button>
        <div class="reply-form-container" id="reply-form-${r.replyId}"></div>
        ${renderReplies(r.children, postId, depth + 1)}
      </div>
    `).join('');
  }

  window.showReplyForm = function (parentReplyId, postId) {
    const container = document.getElementById(`reply-form-${parentReplyId}`);
    if (container.innerHTML) {
      container.innerHTML = "";
      return;
    }

    container.innerHTML = `
      <form class="reply-form" data-post-id="${postId}">
        <input type="hidden" name="parentReplyId" value="${parentReplyId}" />
        <input name="firstName" placeholder="First name" required />
        <input name="lastName" placeholder="Last name" required />
        <textarea name="content" placeholder="Reply..." required></textarea>
        <button type="submit">Reply</button>
      </form>
    `;
  };
});



