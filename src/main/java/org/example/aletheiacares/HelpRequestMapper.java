package org.example.aletheiacares;

import org.springframework.stereotype.Component;

@Component
public class HelpRequestMapper {

    public HelpRequest helpRequest(HelpRequest request){
        HelpRequest helpRequest = new HelpRequest();
        helpRequest.setId(request.getId());
        helpRequest.setUserId(request.getUserId());
        helpRequest.setCategory(request.getCategory());
        helpRequest.setFirstName(request.getFirstName());
        helpRequest.setLastName(request.getLastName());
        helpRequest.setTitle(request.getTitle());
        return helpRequest;
    }

}
