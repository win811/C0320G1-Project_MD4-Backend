package md4.bid_project.services;

import md4.bid_project.models.ApprovementStatus;
import md4.bid_project.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> findProductByOwnerId(Long ownerId);
    List<Product> getAllProduct();

    Product getProductById(Long id);

    void approvementProduct(Product product, ApprovementStatus approvementStatus);

    void unApprovementProduct(Product product);
}
