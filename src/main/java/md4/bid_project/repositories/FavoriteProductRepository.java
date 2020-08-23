package md4.bid_project.repositories;

import md4.bid_project.models.FavoriteProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FavoriteProductRepository extends JpaRepository<FavoriteProduct, Long> {
    // Create: Toàn
    Page<FavoriteProduct> findByUserIdAndStatusIsTrue(Long userId, Pageable pageable);
}
