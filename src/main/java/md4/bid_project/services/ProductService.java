package md4.bid_project.services;

import md4.bid_project.models.Product;
import md4.bid_project.models.User;
import md4.bid_project.models.dto.ProductCreateDTO;

import java.util.List;

public interface ProductService {

    List<Product> findAllProduct();

    Product findProductById(Long id);

    Product saveProduct(ProductCreateDTO productCreateDTO);

    User findOwnerById(Long id);

    List<Product> findProductByOwnerId(Long ownerId);


}
