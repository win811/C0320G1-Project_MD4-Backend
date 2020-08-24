package md4.bid_project.services;

import md4.bid_project.models.Product;

import java.util.List;

public interface ProductService {
    void saveProduct(Product product);

    List<Product> findAll();

    Product findById(long id);
}
