package org.example.aletheiacares;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.*;
import java.util.List;



@Entity
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="first_name", nullable = false)
    private String firstName;

    @Column(name="last_name", nullable = false)
    private String lastName;

    @Column(name="date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name="gender", nullable = false)
    private String gender;

    @Column(name="address", columnDefinition="TEXT")
    private String address;

    @Column(name="marital_status")
    private String maritalStatus;

    @Column(name="has_kids")
    private Boolean hasKids;

    @Column(name="kid_info", columnDefinition="TEXT")
    private String kidInfo;

    @Column(name="member")
    private Boolean member;

    @Column(name="contact_phone")
    private String contactPhone;

    @Column(name="contact_email")
    private String contactEmail;

    @Column(name="attending_since")
    private LocalDate attendingSince;

    // Many-to-many with spiritual_gifts via user_gifts
    @ManyToMany
    @JoinTable(
            name = "user_gifts",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "gift_id")
    )
    private Set<SpiritualGift> gifts = new HashSet<>();


    @ManyToMany
    @JoinTable(
            name = "user_categories",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories = new ArrayList<>();


    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    // One-to-many relationships
    @OneToMany(mappedBy="user", cascade=CascadeType.ALL, orphanRemoval=true)
    private Set<HelpRequest> helpRequests = new HashSet<>();

    @OneToMany(mappedBy="user", cascade=CascadeType.ALL, orphanRemoval=true)
    private Set<Post> posts = new HashSet<>();

    @OneToMany(mappedBy="user", cascade=CascadeType.ALL, orphanRemoval=true)
    private Set<Reply> replies = new HashSet<>();

    @OneToMany(mappedBy="user", cascade=CascadeType.ALL, orphanRemoval=true)
    private Set<UserCategory> userCategories = new HashSet<>();


    public User() {
    }

    public User(Integer id,
                String firstName,
                String lastName,
                LocalDate dateOfBirth,
                String gender,
                String address,
                String maritalStatus,
                Boolean hasKids,
                String kidInfo,
                Boolean member,
                String contactPhone,
                String contactEmail,
                LocalDate attendingSince) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.maritalStatus = maritalStatus;
        this.hasKids = hasKids;
        this.kidInfo = kidInfo;
        this.member = member;
        this.contactPhone = contactPhone;
        this.contactEmail = contactEmail;
        this.attendingSince = attendingSince;
    }

    public Set<SpiritualGift> getGifts() {
        return gifts;
    }

    public void setGifts(Set<SpiritualGift> gifts) {
        this.gifts = gifts;
    }

    public Set<HelpRequest> getHelpRequests() {
        return helpRequests;
    }

    public void setHelpRequests(Set<HelpRequest> helpRequests) {
        this.helpRequests = helpRequests;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Set<Reply> getReplies() {
        return replies;
    }

    public void setReplies(Set<Reply> replies) {
        this.replies = replies;
    }

    public Set<UserCategory> getUserCategories() {
        return userCategories;
    }

    public void setUserCategories(Set<UserCategory> userCategories) {
        this.userCategories = userCategories;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Boolean getHasKids() {
        return hasKids;
    }

    public void setHasKids(Boolean hasKids) {
        this.hasKids = hasKids;
    }

    public String getKidInfo() {
        return kidInfo;
    }

    public void setKidInfo(String kidInfo) {
        this.kidInfo = kidInfo;
    }

    public Boolean getMember() {
        return member;
    }

    public void setMember(Boolean member) {
        this.member = member;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public LocalDate getAttendingSince() {
        return attendingSince;
    }

    public void setAttendingSince(LocalDate attendingSince) {
        this.attendingSince = attendingSince;
    }
}





