package md4.bid_project.services;

import md4.bid_project.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    long APPROVEMENT_STATUS_WAITING = 1L;
    long APPROVEMENT_STATUS_SUCCESS = 2L;

    List<Product> findProductByOwnerId(Long ownerId);

    Page<Product> findApprovedProductsByUserId(Long userId, Pageable pageable);

    Page<Product> findWaitingProductsByUserId(Long userId, Pageable pageable);

    // Creator : Cường
    Page<Product> findProductByOwnerIdAndNameAndApprovementStatus(Long ownerId, String productName,
            String approvementStatusName, Pageable pageable);

    Product findById(Long id);

    void save(Product product);
}
