package org.example.aletheiacares;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HelpRequestRepository extends JpaRepository<HelpRequest, Integer> {

//    List<HelpRequestInterface> findAllProjectedBy();

    HelpRequest findByTitle(String title);

    List<HelpRequest> findByFirstNameAndLastName(String firstName, String lastName);

    @Query("SELECT h.user.id FROM HelpRequest h WHERE h.id = :id")
    Integer findUserIdByHelpRequestId(@Param("id") Integer id);



    @Query("SELECT hr FROM HelpRequest hr LEFT JOIN FETCH hr.user u LEFT JOIN FETCH hr.category c WHERE hr.category.name = :name")
    List<HelpRequest> findByCategoryName(@Param("name") String name);

}
