package md4.bid_project.services;

import md4.bid_project.models.ProductPromotion;
import md4.bid_project.models.dto.ProductPromotionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductPromotionService {

    Page<ProductPromotion> findAll(Pageable pageable);

    Page<ProductPromotion> search(String content, String endOfEvent, String percent ,Pageable pageable );

    List<ProductPromotion> findAll();

    ProductPromotion findById(Long id);

    ProductPromotionDto findProductPromotionDto(Long id);

    ProductPromotion save(ProductPromotionDto productPromotionDto);

    void update(ProductPromotion productPromotion);

    void deleteById(Long id);

    void delete(ProductPromotion productPromotion);

    List<ProductPromotion> search(String content , String saleDate , String endOfEvent,String percent);
}
