package org.example.aletheiacares;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
    public interface HelpRequestRepository extends JpaRepository<HelpRequest, Long> {

    List<HelpRequestInterface> findAllProjectedBy();

    HelpRequest findByTitle(String title);

    List<HelpRequest> findByFirstNameAndLastName(String firstName, String lastName);

    Long findUserId(Long id);





}


