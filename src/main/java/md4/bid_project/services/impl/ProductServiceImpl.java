package md4.bid_project.services.impl;

import md4.bid_project.models.*;
import md4.bid_project.models.dto.ProductCreateDTO;
import md4.bid_project.repositories.*;
import md4.bid_project.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    ProductImageRepository productImageRepository;
    @Autowired
    ProductService productService;

    // Creator: Nguyen Phi Son


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
        productRepository.save(product);

        List<Product> productList =productService.findAllProduct();
        Long idLastProduct = productList.size() +1l ;
        System.out.println("This is product size + 1" + idLastProduct);
        ProductImage productImageObj = new ProductImage();
//            productImageObj.setId(idLastProduct);
        productImageObj.setLink(productCreateDTO.getProductImages());
        Product productImgAdd = productService.findProductById(idLastProduct);
        productImageObj.setProduct(productImgAdd);
        productImageRepository.save(productImageObj);
        return productRepository.save(product);
    }
    @Override
    public void updateProduct(ProductCreateDTO productCreateDTO , Long id) {
        User owner = userRepository.findById(productCreateDTO.getOwnerId()).orElse(null);

        Category category = categoryRepository.findById(productCreateDTO.getCategoryId()).orElse(null);
        ApprovementStatus approvementStatus = approvementStatusRepository
                .findById(1L).orElse(null);
        Product product = new Product();
        product.setId(id);
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
        productRepository.save(product);
    }


    @Override
    public User findOwnerById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> findProductByOwnerId(Long ownerId) {
        return null;
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }



    //    Cường
    @Override
    public Page<Product> findProductByOwnerIdAndNameAndApprovementStatus(Long ownerId, String productName, String approvementStatusName, Pageable pageable) {
        return productRepository.findByOwner_IdAndNameContainingAndApprovementStatus_NameContaining(ownerId,productName,approvementStatusName,pageable);
    }
    //Thành Long
    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }
}
