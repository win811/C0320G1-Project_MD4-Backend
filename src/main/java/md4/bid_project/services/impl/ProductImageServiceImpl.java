package md4.bid_project.services.impl;

import md4.bid_project.models.ProductImage;
import md4.bid_project.repositories.ProductImageRepository;
import md4.bid_project.services.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductImageServiceImpl implements ProductImageService {
    @Autowired
    private ProductImageRepository productImageRepository;

    @Override
    public List<ProductImage> findAll() {
        return productImageRepository.findAll();
    }

    @Override
    public void save(ProductImage productImage) {
        productImageRepository.save(productImage);
    }

    @Override
    public Optional<ProductImage> findById(Long productImageId) {
        return productImageRepository.findAllById(productImageId);
    }

    @Override
    public void saveAll(List<ProductImage> productImages) {
        productImageRepository.saveAll(productImages);
    }
}
