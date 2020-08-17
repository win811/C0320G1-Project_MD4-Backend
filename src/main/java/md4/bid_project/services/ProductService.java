package md4.bid_project.services;

import md4.bid_project.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findProductByOwnerId(Long ownerId);
    List<Product> findAllProduct();

    void save(Product product);

    Optional<Product> findProductById(Long productId);
}
