package md4.bid_project.services;

import md4.bid_project.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    //    Creator : Cường
    Page<Product> findProductByOwnerIdAndNameAndApprovementStatus(Long ownerId, String productName, String approvementStatusName, Pageable pageable);
    Product findById (Long id);
    void save(Product product);

    List<Product> findAll();
}
