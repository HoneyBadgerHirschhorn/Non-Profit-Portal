package org.example.aletheiacares;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class HelpRequestDto {

    private Integer id;  // Add this field

    @NotBlank
    private String title;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotNull
    private Integer userId;

    @NotBlank
    private String category;  // will match Category.name

    @NotBlank
    private String content;

    public HelpRequestDto() { }

    public HelpRequestDto(Integer id, String title,
                          String firstName,
                          String lastName,
                          Integer userId,
                          String category,
                          String content) {
        this.id = id;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId = userId;
        this.category = category;
        this.content = content;
    }




    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
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

    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
