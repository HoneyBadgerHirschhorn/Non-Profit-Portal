package org.example.aletheiacares;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    // Search for posts by author’s first and last name
    List<Post> findByFirstNameAndLastName(String firstName, String lastName);

    List<Post> findByCategory_Name(String name);

    // New method to find posts by help request ID
    List<Post> findByHelpRequest_Id(Integer helpRequestId);
}
