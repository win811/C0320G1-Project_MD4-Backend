package md4.bid_project.services.impl;

import md4.bid_project.models.ProductPromotion;
import md4.bid_project.models.dto.ProductPromotionDto;
import md4.bid_project.repositories.ProductPromotionRepository;
import md4.bid_project.services.ProductPromotionService;
import md4.bid_project.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductPromotionServiceImpl implements ProductPromotionService {
    @Autowired
    private ProductPromotionRepository productPromotionRepository;

    @Autowired
    private ProductService productService;

    @Override
    public List<ProductPromotion> findAll() {
        return productPromotionRepository.findAll();
    }

    @Override
    public ProductPromotion findById(Long id) {
        return productPromotionRepository.findById(id).orElse(null);
    }

    @Override
    public ProductPromotionDto findProductPromotionDto(Long id) {
        ProductPromotion productPromotion=productPromotionRepository.findById(id).orElse(null);
        if (productPromotion!= null) {
            ProductPromotionDto productPromotionDto=new ProductPromotionDto();
            productPromotionDto.setId(productPromotion.getId());
            productPromotionDto.setContent(productPromotion.getContent());
            productPromotionDto.setStartDate(productPromotion.getStartDate());
            productPromotionDto.setEndDate(productPromotion.getEndDate());
            productPromotionDto.setPercent(productPromotion.getPercent());
            productPromotionDto.setPrice(productPromotion.getPrice());
            productPromotionDto.setIdProduct(productPromotion.getProduct().getId());
            return productPromotionDto;
        }
        return null;
    }

    @Override
    public ProductPromotion save(ProductPromotionDto productPromotionDto) {
        ProductPromotion productPromotion=new ProductPromotion();
        productPromotion.setId(productPromotionDto.getId());
        productPromotion.setContent(productPromotionDto.getContent());
        productPromotion.setStartDate(productPromotionDto.getStartDate());
        productPromotion.setEndDate(productPromotionDto.getEndDate());
        productPromotion.setPercent(productPromotionDto.getPercent());
        productPromotion.setPrice(productPromotionDto.getPrice());
        productPromotion.setProduct(productService.findById(productPromotionDto.getIdProduct()));
        productPromotionRepository.save(productPromotion);
        return productPromotion;
    }

    @Override
    public void update(ProductPromotion productPromotion) {
        productPromotionRepository.save(productPromotion);
    }

    @Override
    public void deleteById(Long id) {
        productPromotionRepository.deleteById(id);
    }

    @Override
    public void delete(ProductPromotion productPromotion) {
        productPromotionRepository.delete(productPromotion);
    }
}
