package md4.bid_project.repositories;

import md4.bid_project.models.CommentLevel2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentLevel2Repository extends JpaRepository<CommentLevel2,Long> {

    //Bach
    CommentLevel2 getCommentLevel2ByCommentLevel1_Id(Long commentLevel1Id);
}
