package md4.bid_project.services;

import md4.bid_project.models.ApprovementStatus;
import md4.bid_project.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    //Thành Long
    List<Product> getAllProduct();

    //Thành Long
    Product getProductById(Long id);

    //Thành Long
    void approvementProduct(Product product, ApprovementStatus approvementStatus);

    //Thành Long
    void unApprovementProduct(Product product);
    //    Cường
    Page<Product> findProductByOwnerIdAndNameAndApprovementStatus(Long ownerId, String productName, String approvementStatusName, Pageable pageable);
    Product findById (Long id);
    void save(Product product);
}
