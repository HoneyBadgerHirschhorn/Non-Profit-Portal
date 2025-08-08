package org.example.aletheiacares;

import jakarta.persistence.*;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "help_request_id", nullable = false)
    private HelpRequest helpRequest;

    // Constructors
    public Post() {
        this.createdAt = LocalDateTime.now();
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public HelpRequest getHelpRequest() {
        return helpRequest;
    }

    public void setHelpRequest(HelpRequest helpRequest) {
        this.helpRequest = helpRequest;
    }

        public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}










//package org.example.aletheiacares;
//
//import java.time.LocalDateTime;
//import java.util.HashSet;
//import java.util.Set;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "posts")
//public class Post {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "post_id")
//    private Integer postId;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @Column(name = "first_name")
//    private String firstName;
//
//    @Column(name = "last_name")
//    private String lastName;
//
//    @Column(name = "title", nullable = false, length = 255)
//    private String title;
//
//    @Column(name = "content", columnDefinition = "LONGTEXT", nullable = false)
//    private String content;
//
//    @Column(name = "created_at")
//    private LocalDateTime createdAt;
//
//    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference
//    private Set<Reply> replies = new HashSet<>();
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "category_id", nullable = false)
//    private Category category;
//
//    public Post() { }
//
//    public Post(User user,
//                String firstName,
//                String lastName,
//                String title,
//                String content,
//                LocalDateTime createdAt) {
//        this.user = user;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.title = title;
//        this.content = content;
//        this.createdAt = createdAt;
//    }
//
//    public Integer getPostId() {
//        return postId;
//    }
//
//    public void setPostId(Integer postId) {
//        this.postId = postId;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(LocalDateTime createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public Category getCategory() { return category; }
//
//    public void setCategory(Category category) { this.category = category; }
//}
//
////    @JsonIgnore // Prevent recursion
////    public Set<Reply> getReplies() {
////        return replies;
////    }
////    public void setReplies(Set<Reply> replies) {
////        this.replies = replies;
////    }
//
