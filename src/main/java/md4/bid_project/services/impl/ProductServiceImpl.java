package md4.bid_project.services.impl;

import md4.bid_project.models.Product;
import md4.bid_project.repositories.ProductRepository;
import md4.bid_project.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> findProductByOwnerId(Long ownerId) {
        return productRepository.findByOwner_Id(ownerId);
    }

    @Override
    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public void save(Product product) {
        product.getApprovementStatus().setId(1L);
        productRepository.save(product);
    }

    @Override
    public Optional<Product> findProductById(Long productId) {
        return productRepository.findAllById(productId);
    }
}
