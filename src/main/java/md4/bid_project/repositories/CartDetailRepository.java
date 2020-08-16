package md4.bid_project.repositories;

import md4.bid_project.models.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail,Long> {
    List<CartDetail> findAllByCart_Id(Long id);
}
