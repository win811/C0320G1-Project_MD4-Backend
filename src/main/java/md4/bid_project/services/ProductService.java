package md4.bid_project.services;

import md4.bid_project.models.ApprovementStatus;
import md4.bid_project.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    //    Cường
    //    Creator : Cường
    Page<Product> findProductByOwnerIdAndNameAndApprovementStatus(Long ownerId, String productName, String approvementStatusName, Pageable pageable);

    Product findById (Long id);

    List<Product> findProductByOwnerId(Long ownerId);
    List<Product> findAllProduct();

    // Thành Long
    Page<Product> findAllProduct(int page);

    //Thành Long
    Product getProductById(Long id);

    //Thành Long
    void approvementProduct(Product product, ApprovementStatus approvementStatus);

    //Thành Long
    void unApprovementProduct(Product product, ApprovementStatus approvementStatus);

    //Thành Long
    void deleteProduct(Product product);

    //Thành Long
    Page<Product> findCustomerByCriteria(Specification<Product> spec, int page);
    Specification<Product> getFilter(String name, String category, String minPrice, String maxPrice, String owner, String status);

    //Thành
    void save(Product product);
    //Thành
    Optional<Product> findProductById(Long productId);
}
