package org.example.aletheiacares;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "replies")
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private Integer replyId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonBackReference
    private Post post;
    @ManyToOne
    @JoinColumn(name = "parent_reply_id")
    @JsonBackReference
    private Reply parentReply;
    @OneToMany(mappedBy = "parentReply", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Reply> childReplies = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "content", columnDefinition = "LONGTEXT", nullable = false)
    private String content;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Reply() { }

    public Reply(Post post,
                 Reply parentReply,
                 User user,
                 String firstName,
                 String lastName,
                 String content,
                 LocalDateTime createdAt) {
        this.post = post;
        this.parentReply = parentReply;
        this.user = user;
        this.firstName = firstName;
        this.lastName = lastName;
        this.content = content;
        this.createdAt = createdAt;
    }

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Reply getParentReply() {
        return parentReply;
    }

    public void setParentReply(Reply parentReply) {
        this.parentReply = parentReply;
    }

    @JsonIgnore // Prevent recursion
    public Set<Reply> getChildReplies() {
        return childReplies;
    }
    public void setChildReplies(Set<Reply> childReplies) {
        this.childReplies = childReplies;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}
