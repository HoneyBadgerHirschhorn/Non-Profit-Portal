package org.example.aletheiacares;

import jakarta.persistence.*;

@Entity
@Table(name = "user_categories")
public class UserCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /** The user who this category assignment belongs to */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /** The category assigned to the user */
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "first_name", nullable = false, length = 255)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 255)
    private String lastName;

    public UserCategory() { }

    public UserCategory(User user, Category category, String firstName, String lastName) {
        this.user = user;
        this.category = category;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // —— Getters & Setters —— //

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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
}
