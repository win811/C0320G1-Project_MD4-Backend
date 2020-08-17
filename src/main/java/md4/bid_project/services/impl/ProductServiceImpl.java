package md4.bid_project.services.impl;

import jdk.vm.ci.meta.Constant;
import md4.bid_project.models.ApprovementStatus;
import md4.bid_project.models.Product;
import md4.bid_project.repositories.ApprovementStatusRepository;
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
    ApprovementStatusRepository approvementStatusRepository;
    @Override
    public List<Product> findProductByOwnerId(Long ownerId) {
        return productRepository.findByOwner_Id(ownerId);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }
    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void approvementProduct(Product product, ApprovementStatus approve) {

        product.setApprovementStatus(approve);
        productRepository.save(product);
    }
    @Override
    public void unApprovementProduct(Product product) {
        productRepository.save(product);
    }
}
