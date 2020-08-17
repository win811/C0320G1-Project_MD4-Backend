package md4.bid_project.services;

import md4.bid_project.models.ProductImage;

import java.util.List;
import java.util.Optional;

public interface ProductImageService {
    List<ProductImage> findAll();

    void save(ProductImage productImage);

     Optional<ProductImage> findById(Long productImageId);

    void saveAll(List<ProductImage> productImages);
}
