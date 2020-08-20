package md4.bid_project.services.impl;

import md4.bid_project.models.ProductPromotion;
import md4.bid_project.repositories.ProductPromotionRepository;
import md4.bid_project.services.ProductPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductPromotionServiceImpl implements ProductPromotionService {

    @Autowired
    ProductPromotionRepository repository;

    @Override
    public List<ProductPromotion> findAll() {
        return repository.findAll();
    }

    @Override
    public ProductPromotion findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<ProductPromotion> search(String content, String saleDate, String endOfEvent, String percent) {
        return repository.getAllProductBySearch(content, saleDate, endOfEvent, percent);
    }
}
