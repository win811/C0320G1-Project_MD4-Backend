package md4.bid_project.services.impl;

import md4.bid_project.models.ApprovementStatus;
import md4.bid_project.models.Category;
import md4.bid_project.models.Product;
import md4.bid_project.models.User;
import md4.bid_project.models.dto.ProductCreateDTO;
import md4.bid_project.repositories.ApprovementStatusRepository;
import md4.bid_project.repositories.CategoryRepository;
import md4.bid_project.repositories.ProductRepository;
import md4.bid_project.repositories.UserRepository;
import md4.bid_project.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ApprovementStatusRepository approvementStatusRepository;

    @Override
    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product findProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product saveProduct(ProductCreateDTO productCreateDTO) {
        User owner = userRepository.findById(productCreateDTO.getOwnerId()).orElse(null);
        Category category = categoryRepository.findById(productCreateDTO.getCategoryId()).orElse(null);
        ApprovementStatus approvementStatus = approvementStatusRepository
                .findById(1L).orElse(null);
        Product product = new Product();
        product.setName(productCreateDTO.getName());
        product.setOwner(owner);
        product.setCategory(category);
        product.setInitialPrice(productCreateDTO.getInitialPrice());
        product.setIncreaseAmount(productCreateDTO.getIncreaseAmount());
        product.setRegisterDate(productCreateDTO.getRegisterDate());
        product.setStartDate(productCreateDTO.getStartDate());
        product.setEndDate(productCreateDTO.getEndDate());
        product.setDescription(productCreateDTO.getDescription());
        product.setApprovementStatus(approvementStatus);
        return productRepository.save(product);
    }

    @Override
    public User findOwnerById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> findProductByOwnerId(Long ownerId) {
        return productRepository.findByOwner_Id(ownerId);
    }

}
