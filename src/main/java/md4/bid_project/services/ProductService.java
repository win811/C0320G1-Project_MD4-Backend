package md4.bid_project.services;

import md4.bid_project.models.ApprovementStatus;
import md4.bid_project.models.Product;
import md4.bid_project.models.dto.ProductListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import md4.bid_project.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    long APPROVEMENT_STATUS_WAITING = 1L;
    long APPROVEMENT_STATUS_SUCCESS = 2L;

    List<Product> findProductByOwnerId(Long ownerId);

    //    Creator : Toàn
    Page<Product> findApprovedProductsByUserId(Long userId, Pageable pageable);
    //    Creator : Toàn
    Page<Product> findWaitingProductsByUserId(Long userId, Pageable pageable);

    // Creator : Cường
    Page<Product> findProductByOwnerIdAndNameAndApprovementStatus(Long ownerId, String productName,
            String approvementStatusName, Pageable pageable);

    Product findById(Long id);

    List<Product> findAllProduct();

    // Thành Long
    Page<ProductListDTO> findAllProduct(int page);

    //Thành Long
    Product getProductById(Long id);

    //Thành Long
    ProductListDTO checkProduct(Long id);

    //Thành Long
    void approvementProduct(Product product, ApprovementStatus approvementStatus);

    //Thành Long
    void unApprovementProduct(Product product, ApprovementStatus approvementStatus);

    //Thành Long
    void deleteProduct(Product product);

    //Thành Long
    Page<ProductListDTO> findCustomerByCriteria(Specification<Product> spec, int page);
    Specification<Product> getFilter(String name, String category, String minPrice, String maxPrice, String owner, String status);

    //Thành
    void save(Product product);

    List<Product> findAll();
    //Thành
    Optional<Product> findProductById(Long productId);
}
