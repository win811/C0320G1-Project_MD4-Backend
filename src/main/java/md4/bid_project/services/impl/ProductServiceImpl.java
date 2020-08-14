package md4.bid_project.services.impl;

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
    private ProductRepository productRepository;

    public void getSomething() {
        return;
    }

    public void doSomething1(){
        System.out.println("a");
    }

    @Override
    public Page<Product> getAllProduct(Pageable pageable) {
        return productRepository.findAll(pageable) ;
    }
}
