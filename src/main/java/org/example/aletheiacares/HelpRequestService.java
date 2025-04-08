package org.example.aletheiacares;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class HelpRequestService {

    @Autowired
    private HelpRequestRepository helpRequestRepository;

    public HelpRequest saveHelpRequest(HelpRequest helpRequest) {

        List<HelpRequestInterface> moo = getAllHelpRequestSummaries();
        boolean button1 = false;
        boolean button2 = false;
        String firstName;
        String LastName;

        if(moo.contains(helpRequest.getFirstName())){
            button1 = true;
            firstName = helpRequest.getFirstName();
        }

        if(moo.contains(helpRequest.getLastName())){
            button2 = true;
            LastName = helpRequest.getLastName();
        }

        if (button1 && button2 == true) {
            List<HelpRequest> names = helpRequestRepository.findByFirstNameAndLastName(helpRequest.getFirstName(), helpRequest.getLastName());
        }

        List<HelpRequest> matches = helpRequestRepository.findByFirstNameAndLastName(helpRequest.getFirstName(),helpRequest.getLastName());

        if (!matches.isEmpty()) {
            // A HelpRequest exists in the DB with the same first and last name
            helpRequest.setUserId(matches.getFirst().getUserId());
        }

        return helpRequestRepository.save(helpRequest);
    }

         public List<HelpRequestInterface> getAllHelpRequestSummaries() {
        return helpRequestRepository.findAllProjectedBy();
    }
}



