package md4.bid_project.services.impl;

import md4.bid_project.models.Product;
import md4.bid_project.repositories.ProductRepository;
import md4.bid_project.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }
}
