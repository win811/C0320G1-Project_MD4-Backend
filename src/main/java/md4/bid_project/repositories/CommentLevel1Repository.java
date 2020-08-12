package md4.bid_project.repositories;

import md4.bid_project.models.CommentLevel1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentLevel1Repository extends JpaRepository<CommentLevel1, Long> {
}
