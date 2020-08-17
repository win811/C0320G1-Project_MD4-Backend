package md4.bid_project.services.impl;

import md4.bid_project.models.Product;
import md4.bid_project.repositories.ProductRepository;
import md4.bid_project.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    // Create: Toàn
    @Override
    public List<Product> findProductByOwnerId(Long ownerId) {
        return productRepository.findByOwner_Id(ownerId);
    }

    // Create: Toàn
    @Override
    public Page<Product> findApprovedProductsByUserId(Long userId, Pageable pageable) {
        return productRepository.findByOwner_IdAndApprovementStatus_Id(userId, APPROVEMENT_STATUS_SUCCESS, pageable);
    }

    // Create: Toàn
    @Override
    public Page<Product> findWaitingProductsByUserId(Long userId, Pageable pageable) {
        return productRepository.findByOwner_IdAndApprovementStatus_Id(userId, APPROVEMENT_STATUS_WAITING, pageable);
    }
}
