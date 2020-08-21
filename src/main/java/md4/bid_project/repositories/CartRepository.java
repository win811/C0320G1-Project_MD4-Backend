package md4.bid_project.repositories;

import md4.bid_project.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    // Create: Toàn
    Optional<Cart> findByUserIdAndStatusIsTrue(Long userId);
}
