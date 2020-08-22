package md4.bid_project.repositories;

import md4.bid_project.models.ProductPromotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductPromotionRepository extends JpaRepository<ProductPromotion,Long> {

//    //creator:Tiến
//    List<ProductPromotion> findAllByFlagIsTrue();

    //creator:Tiến
    @Query(value= "select * from product_promotion p where (p.product_promotion_content like %?1%) and p.product_promotion_start_date <= ?2 and p.product_promotion_end_date >= ?3 and p.product_promotion_percent <= ?4" , nativeQuery=true)
    List<ProductPromotion> getAllProductBySearch(String content, String saleDate, String endOfEvent, String percent);
}
