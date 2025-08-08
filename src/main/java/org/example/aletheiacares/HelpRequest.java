package org.example.aletheiacares;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name = "help_requests")
public class HelpRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("status")
    private String status;

    @JsonProperty("title")
    private String title;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @JsonProperty("content")
    private String content;



    // Constructors
    public HelpRequest() {}

    public HelpRequest( String status, String title, String firstName, String lastName, User user, Category category,  String content) {
        this.status = status;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.user = user;
        this.content = content;
        this.category = category;
    }

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public User getUser() { return user; }
    public void setUser(User userId) { this.user = userId; }


    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}
