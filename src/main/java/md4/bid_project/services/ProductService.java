package md4.bid_project.services;

import md4.bid_project.models.ApprovementStatus;
import md4.bid_project.models.Product;
import md4.bid_project.models.dto.ProductSearchField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;


public interface ProductService {

    //Thành Long
    Product getProductById(Long id);

    //Thành Long
    void approvementProduct(Product product, ApprovementStatus approvementStatus);

    //Thành Long
    void unApprovementProduct(Product product, ApprovementStatus approvementStatus);
    //    Cường
    //    Creator : Cường
    Page<Product> findProductByOwnerIdAndNameAndApprovementStatus(Long ownerId, String productName, String approvementStatusName, Pageable pageable);

    // Thành Long
    Page<Product> findAllProduct(Pageable pageable);

    Product findById (Long id);

    void save(Product product);

    void deleteProduct(Product product);

    Page<Product> findCustomerByCriteria(Specification<Product> spec, int page);
    Specification<Product> getFilter(ProductSearchField productSearchField);

}
