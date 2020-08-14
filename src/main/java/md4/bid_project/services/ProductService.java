package md4.bid_project.services;

import md4.bid_project.models.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAllProduct();
    Product findProductById(Long id);
    void saveProduct(Product product);
    
    List<Product> findProductByOwnerId(Long ownerId);

}
