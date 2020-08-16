package md4.bid_project.repositories;

import md4.bid_project.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    Cart findAllByUser_IdAndStatusTrue(Long userId);
}
