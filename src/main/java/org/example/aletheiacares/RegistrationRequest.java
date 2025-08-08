package org.example.aletheiacares;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class RegistrationRequest {
    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("date_of_birth")
    private LocalDate dateOfBirth;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("address")
    private String address;

    @JsonProperty("marital_status")
    private String maritalStatus;

    @JsonProperty("has_kids")
    private Boolean hasKids;

    @JsonProperty("kid_info")
    private String kidInfo;

    @JsonProperty("member")
    private Boolean member;

    @JsonProperty("contact_phone")
    private String contactPhone;

    @JsonProperty("contact_email")
    private String contactEmail;

    @JsonProperty("attending_since")
    private LocalDate attendingSince;


    public RegistrationRequest() {}

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

