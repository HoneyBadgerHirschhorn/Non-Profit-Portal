package org.example.aletheiacares;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User>findUserByFirstNameAndLastName(String firstName, String lastName);

    Optional<User> findByContactEmail(String contactEmail);

}
