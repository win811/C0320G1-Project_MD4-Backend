package md4.bid_project.services.Impl;

import md4.bid_project.models.product.ProductEntity;
import md4.bid_project.repositories.ProductRepository;
import md4.bid_project.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service

public class ProductServicesImpl implements ProductServices {

    @Autowired
    ProductRepository productRepository;
//    @Override
//    public List<ProductEntity> findAllProduct() {
//        return productRepository.findAll();
//    }
//
//    @Override
//    public ProductEntity findProductById(Long id) {
//        return productRepository.findById(id).orElse(null);
//    }
}
