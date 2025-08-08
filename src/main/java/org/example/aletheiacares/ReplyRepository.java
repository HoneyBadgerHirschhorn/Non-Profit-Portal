

package org.example.aletheiacares;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Integer> {
    List<Reply> findByPost_PostId(Integer postId);

    List<Reply> findByFirstNameAndLastName(String firstName, String lastName);
}
