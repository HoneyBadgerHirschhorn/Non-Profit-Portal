package org.example.aletheiacares;




import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UserMapper {

    public User toEntity(UserDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setDateOfBirth(dto.getDateOfBirth());
        user.setGender(dto.getGender());
        user.setAddress(dto.getAddress());
        user.setMaritalStatus(dto.getMaritalStatus());
        user.setHasKids(dto.getHasKids());
        user.setKidInfo(dto.getKidInfo());
        user.setMember(dto.getMember());
        user.setContactPhone(dto.getContactPhone());
        user.setContactEmail(dto.getContactEmail());
        user.setAttendingSince(dto.getAttendingSince());
        // Gifts & Categories can be populated if needed
        return user;
    }

    public UserDto registrationRequestToDto(RegistrationRequest request) {
        if (request == null) return null;
        UserDto dto = new UserDto();
        dto.setFirstName(request.getFirstName());
        dto.setLastName(request.getLastName());
        dto.setDateOfBirth(request.getDateOfBirth());
        dto.setGender(request.getGender());
        dto.setAddress(request.getAddress());
        dto.setMaritalStatus(request.getMaritalStatus());
        dto.setHasKids(request.getHasKids());
        dto.setKidInfo(request.getKidInfo());
        dto.setMember(request.getMember());
        dto.setContactPhone(request.getContactPhone());
        dto.setContactEmail(request.getContactEmail());
        dto.setAttendingSince(request.getAttendingSince());
        return dto;
    }

    public UserDto toDto(User user) {
        if (user == null) return null;
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setDateOfBirth(user.getDateOfBirth());
        dto.setGender(user.getGender());
        dto.setAddress(user.getAddress());
        dto.setMaritalStatus(user.getMaritalStatus());
        dto.setHasKids(user.getHasKids());
        dto.setKidInfo(user.getKidInfo());
        dto.setMember(user.getMember());
        dto.setContactPhone(user.getContactPhone());
        dto.setContactEmail(user.getContactEmail());
        dto.setAttendingSince(user.getAttendingSince());
        Set<Integer> giftIds = new HashSet<>();
        if (user.getGifts() != null) {
            for (SpiritualGift gift : user.getGifts()) {
                giftIds.add(gift.getId());
            }
        }
        dto.setGiftIds(giftIds);

        List<Integer> categoryIds = new ArrayList<>();
        if (user.getCategories() != null) {
            for (Category category : user.getCategories()) {
                categoryIds.add(category.getId());
            }
        }
        dto.setCategoryIds(categoryIds);

        return dto;
    }
}