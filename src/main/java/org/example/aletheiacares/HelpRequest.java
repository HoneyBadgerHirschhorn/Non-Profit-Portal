


package org.example.aletheiacares;

import jakarta.persistence.*; // Import JPA annotations
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "help_requests") // Optional: Explicit table name
public class HelpRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate primary key
    @JsonProperty("id")
    private Integer version;

    @Version
    @JsonProperty("version")


    private Long id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("category")
    private String category;

    @JsonProperty("content")
    private String content;

    // Constructors
    public HelpRequest() {}

    public HelpRequest(String title, String firstName, String lastName, Long userId, String category, String content) {
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId = userId;
        this.category = category;
        this.content = content;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}


//package org.example.aletheiacares;
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//
//public class HelpRequest {
//    @JsonProperty("id")
//    private Long id;
//
//    @JsonProperty("title")
//    private String title;
//
//    @JsonProperty("first_name")
//    private String firstName;
//
//    @JsonProperty("last_name")
//    private String lastName;
//
//    @JsonProperty("user_id")
//    private Long userId;
//
//    @JsonProperty("category")
//    private String category;
//
//    @JsonProperty("content")
//    private String content;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
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
//    public Long getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }
//
//    public String getCategory() {
//        return category;
//    }
//
//    public void setCategory(String category) {
//        this.category = category;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//}