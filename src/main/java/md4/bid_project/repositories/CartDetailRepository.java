package md4.bid_project.repositories;

import md4.bid_project.models.CartDetail;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail,Long> {

    //created by Thao
    List<CartDetail> findAllByIsDeleteIsFalse(Pageable pageable);

    //created by Thao
    List<CartDetail> findAllByIsDeleteIsFalse();

}
