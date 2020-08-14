package md4.bid_project.services;

import md4.bid_project.models.Product;

import md4.bid_project.models.Product;

import java.util.List;

public interface ProductService {

    Product getProductById(Long id);

    List<Product> findProductByOwnerId(Long ownerId);
}
