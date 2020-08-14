package md4.bid_project.services;

import md4.bid_project.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Page<Product> getAllProduct(Pageable pageable);
}
