package md4.bid_project.services.Impl;

import md4.bid_project.models.Product;
import md4.bid_project.repositories.ProductRepository;
import md4.bid_project.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    //    Creator : Cường
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
}
