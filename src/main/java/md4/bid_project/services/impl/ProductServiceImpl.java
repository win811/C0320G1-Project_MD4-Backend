package md4.bid_project.services.impl;

import jdk.vm.ci.meta.Constant;
import md4.bid_project.models.ApprovementStatus;
import md4.bid_project.models.Product;
import md4.bid_project.repositories.ApprovementStatusRepository;
import md4.bid_project.repositories.ProductRepository;
import md4.bid_project.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    //    Cường
    @Override
    public Page<Product> findProductByOwnerIdAndNameAndApprovementStatus(Long ownerId, String productName, String approvementStatusName, Pageable pageable) {
        return productRepository.findByOwner_IdAndNameContainingAndApprovementStatus_NameContaining(ownerId,productName,approvementStatusName,pageable);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    //Thành Long
    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    //Thành Long
    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    //Thành Long
    @Override
    public void approvementProduct(Product product, ApprovementStatus approve) {
        product.setApprovementStatus(approve);
        productRepository.save(product);
    }

    //Thành Long
    @Override
    public void unApprovementProduct(Product product) {
        productRepository.save(product);
    }
}
