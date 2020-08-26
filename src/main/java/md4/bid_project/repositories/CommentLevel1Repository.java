package md4.bid_project.repositories;

import md4.bid_project.models.CommentLevel1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentLevel1Repository extends JpaRepository<CommentLevel1, Long> {

    List<CommentLevel1> findAllByProduct_Id(Long productId);
}
