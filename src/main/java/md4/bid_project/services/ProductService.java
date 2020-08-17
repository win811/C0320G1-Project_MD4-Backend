package md4.bid_project.services;

import md4.bid_project.models.Product;
import md4.bid_project.models.User;
import md4.bid_project.models.dto.ProductCreateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    //    Cường
    Page<Product> findProductByOwnerIdAndNameAndApprovementStatus(Long ownerId, String productName, String approvementStatusName, Pageable pageable);


    //Creator: Nguyen Phi Son
    Product findById (Long id);
    void save(Product product);

    List<Product> findAllProduct();

    Product findProductById(Long id);

    Product saveProduct(ProductCreateDTO productCreateDTO);

    User findOwnerById(Long id);

    List<Product> findProductByOwnerId(Long ownerId);


}
