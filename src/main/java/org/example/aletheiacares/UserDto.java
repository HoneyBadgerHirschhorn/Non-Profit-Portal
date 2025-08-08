package org.example.aletheiacares;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

    public class UserDto {

        private Integer id;
        private String firstName;
        private String lastName;
        private LocalDate dateOfBirth;
        private String gender;
        private String address;
        private String maritalStatus;
        private Boolean hasKids;
        private String kidInfo;
        private Boolean member;
        private String contactPhone;
        private String contactEmail;
        private LocalDate attendingSince;

        // Use IDs or names for related entities in the DTO
        private Set<Integer> giftIds;
        private List<Integer> categoryIds;

        // Constructors
        public UserDto() {
        }

        public UserDto(Integer id, String firstName, String lastName, LocalDate dateOfBirth,
                       String gender, String address, String maritalStatus, Boolean hasKids,
                       String kidInfo, Boolean member, String contactPhone, String contactEmail,
                       LocalDate attendingSince, Set<Integer> giftIds, List<Integer> categoryIds) {
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
            this.giftIds = giftIds;
            this.categoryIds = categoryIds;
        }

        // Getters and setters
        // (Generate via IDE or Lombok if preferred)

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

        public Set<Integer> getGiftIds() {
            return giftIds;
        }

        public void setGiftIds(Set<Integer> giftIds) {
            this.giftIds = giftIds;
        }

        public List<Integer> getCategoryIds() {
            return categoryIds;
        }

        public void setCategoryIds(List<Integer> categoryIds) {
            this.categoryIds = categoryIds;
        }
    }


