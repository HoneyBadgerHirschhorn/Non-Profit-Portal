package org.example.aletheiacares;

import java.time.LocalDate;

public  class RegistrationRequest {
    private String first_name;
    private String last_name;
    private LocalDate date_of_birth;
    private String gender;
    private String address;
    private String marital_status;
    private boolean has_kids;
    private String kid_info;
    private boolean member;
    private String contact_phone;
    private String contact_email;
    private LocalDate attending_since;

    // Getters and setters
    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
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

    public String getMarital_status() {
        return marital_status;
    }

    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }

    public boolean isHas_kids() {
        return has_kids;
    }

    public void setHas_kids(boolean has_kids) {
        this.has_kids = has_kids;
    }

    public String getKid_info() {
        return kid_info;
    }

    public void setKid_info(String kid_info) {
        this.kid_info = kid_info;
    }

    public boolean isMember() {
        return member;
    }

    public void setMember(boolean member) {
        this.member = member;
    }

    public String getContact_phone() {
        return contact_phone;
    }

    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
    }

    public String getContact_email() {
        return contact_email;
    }

    public void setContact_email(String contact_email) {
        this.contact_email = contact_email;
    }

    public LocalDate getAttending_since() {
        return attending_since;
    }

    public void setAttending_since(LocalDate attending_since) {
        this.attending_since = attending_since;
    }
}

