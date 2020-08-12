package md4.bid_project.repositories;

import md4.bid_project.models.Lv1Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Lv1CommentRepository extends JpaRepository<Lv1Comment, Long> {
}
