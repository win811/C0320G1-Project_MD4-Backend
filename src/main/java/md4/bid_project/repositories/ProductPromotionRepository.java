package md4.bid_project.repositories;

import md4.bid_project.models.ProductPromotion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductPromotionRepository extends JpaRepository<ProductPromotion,Long> {
    //creator:Tiến
    @Async
    @Query(value= "select * from product_promotions p where p.product_promotion_content like %:content%  and p.product_promotion_end_date <= :endOfEvent and p.product_promotion_percent <= :percent",nativeQuery = true)
    Page<ProductPromotion> getAllProductBySearch(
            @Param("content") String content,
            @Param("endOfEvent") String endOfEvent,
            @Param("percent") String percent,
            Pageable pageable
    );
}
