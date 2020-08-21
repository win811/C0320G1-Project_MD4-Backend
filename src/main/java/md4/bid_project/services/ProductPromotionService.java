package md4.bid_project.services;

import md4.bid_project.models.ProductPromotion;

import java.util.List;

public interface ProductPromotionService {

    List<ProductPromotion> findAll();

    ProductPromotion findById(Long id);

    List<ProductPromotion> search(String content , String saleDate , String endOfEvent,String percent);

}
