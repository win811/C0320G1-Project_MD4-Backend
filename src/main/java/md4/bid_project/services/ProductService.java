package md4.bid_project.services;

import md4.bid_project.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    //    Cường
    Page<Product> findProductByOwnerIdAndNameAndApprovementStatus(Long ownerId, String productName, String approvementStatusName, Pageable pageable);
    Product findById (Long id);
    List<Product> findProductByOwnerId(Long ownerId);
    List<Product> findAllProduct();

    void save(Product product);

    Optional<Product> findProductById(Long productId);
}
