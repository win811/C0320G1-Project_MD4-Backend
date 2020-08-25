package md4.bid_project.services;

import md4.bid_project.models.ProductImage;

import java.util.List;
import java.util.Optional;

public interface ProductImageService {
    List<ProductImage> findAll();
    //Thành
    void save(ProductImage productImage);
    //Thành
     Optional<ProductImage> findById(Long productImageId);
    //Thành
    void saveAll(List<ProductImage> productImages);
}
