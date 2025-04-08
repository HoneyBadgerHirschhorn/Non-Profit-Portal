package org.example.aletheiacares;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapRequest(RegistrationRequest request){
        User user = new User();
        user.setFirst_name(request.getFirst_name());
        user.setLast_name(request.getLast_name());
        user.setDate_of_birth(request.getDate_of_birth());
        user.setGender(request.getGender());
        user.setAddress(request.getAddress());
        user.setMarital_status(request.getMarital_status());
        user.setHas_kids(request.isHas_kids());
        user.setKid_info(request.getKid_info());
        user.setMember(request.isMember());
        user.setContact_phone(request.getContact_phone());
        user.setContact_email(request.getContact_email());
        user.setAttending_since(request.getAttending_since());
        return user;
    }
}
