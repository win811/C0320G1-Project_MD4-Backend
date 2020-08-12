package md4.bid_project.repositories;

import md4.bid_project.models.ProductPromotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductPromotionRepository  extends JpaRepository<ProductPromotion,Long> {
}
