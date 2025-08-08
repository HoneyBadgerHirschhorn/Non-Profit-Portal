package org.example.aletheiacares;

import jakarta.validation.constraints.NotBlank;

public class PostDto {

    private Integer postId;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    /**
     * Formatted timestamp, e.g. "2025-06-17 14:23:05"
     */
    private String createdAt;

    @NotBlank
    private String category;

    // Constructors
    public PostDto() {}

    // Getters and Setters
    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}



//package org.example.aletheiacares;
//
//import jakarta.validation.constraints.NotBlank;
//
//public class PostDto {
//
//
//    private Integer postId;
//
//    @NotBlank
//    private String firstName;
//
//    @NotBlank
//    private String lastName;
//
//    @NotBlank
//    private String title;
//
//    @NotBlank
//    private String content;
//
//    @NotBlank
//    private String category;
//
//
//
//
//    private String createdAt;
//
//    public PostDto() { }
//
//    public Integer getPostId() { return postId; }
//    public void setPostId(Integer postId) { this.postId = postId; }
//
//    public String getCreatedAt() { return createdAt; }
//    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
//
//    public String getFirstName() {return firstName;}
//    public void setFirstName(String firstName) {this.firstName = firstName;}
//
//    public String getLastName() {return lastName;}
//    public void setLastName(String lastName) {this.lastName = lastName;}
//
//    public String getTitle() {return title;}
//    public void setTitle(String title) {this.title = title;}
//
//    public String getContent() {return content;}
//    public void setContent(String content) {this.content = content;}
//
//    public String getCategory() { return category; }
//    public void setCategory(String category) { this.category = category; }
//}
