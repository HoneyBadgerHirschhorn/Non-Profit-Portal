package org.example.aletheiacares;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HelpRequestService {

    private final HelpRequestRepository helpRequestRepository;

    @Autowired
    public HelpRequestService(HelpRequestRepository helpRequestRepository) {
        this.helpRequestRepository = helpRequestRepository;
    }

    public HelpRequest saveHelpRequest(HelpRequest helpRequest) {
        // Optional: implement deduplication based on first + last name
        List<HelpRequest> existing = helpRequestRepository.findByFirstNameAndLastName(
                helpRequest.getFirstName(), helpRequest.getLastName()
        );

        if (!existing.isEmpty()) {
            helpRequest.setUser(existing.get(0).getUser()); // Reuse user if it exists
        }

        return helpRequestRepository.save(helpRequest);
    }

    public List<HelpRequest> getByCategoryName(String categoryName) {
        return helpRequestRepository.findByCategoryName(categoryName);
    }
}
