package org.example.aletheiacares;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ReplyDto {

    @NotNull
    private Integer postId;

    private Integer parentReplyId;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String content;

    private String createdAt;

    // Constructors, getters, and setters below

    public ReplyDto() { }

    public Integer getPostId() { return postId; }
    public void setPostId(Integer postId) { this.postId = postId; }

    public Integer getParentReplyId() { return parentReplyId; }
    public void setParentReplyId(Integer parentReplyId) { this.parentReplyId = parentReplyId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}
