package md4.bid_project.controllers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import md4.bid_project.models.Product;
import md4.bid_project.models.ProductImage;
import md4.bid_project.models.dto.ProductCreateDTO;
import md4.bid_project.models.dto.ProductImageDto;
import md4.bid_project.repositories.CategoryRepository;
import md4.bid_project.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
//Creator: Nguyen Thanh Tu
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ApprovementStatusService approvementStatusService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductImageService productImageService;

    @GetMapping("")
    public ResponseEntity<List<Product>> listAllProducts() {
        List<Product> products = productService.findAll();
        if (products.isEmpty()) {
            return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    @GetMapping(value ="/list/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProduct(@PathVariable("id") long id) {
        Product product = productService.findById(id);
        if (product == null) {
            return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @PostMapping("")
    private ResponseEntity<Product> createNewProduct(@RequestBody ProductCreateDTO productCreateDTO ){
        Product product = new Product();
        ProductImage productImage = new ProductImage();
        List<String> productImageLinks;

        product.setName(productCreateDTO.getName());
        product.setApprovementStatus(approvementStatusService.findById(productCreateDTO.getApprovementStatusId()));
        product.setDescription(productCreateDTO.getDescription());
        product.setRegisterDate(productCreateDTO.getRegisterDate());
        product.setStartDate(productCreateDTO.getStartDate());
        product.setEndDate(productCreateDTO.getEndDate());
        product.setInitialPrice(productCreateDTO.getInitialPrice());
        product.setIncreaseAmount(productCreateDTO.getIncreaseAmount());
        product.setCategory(categoryService.findById(productCreateDTO.getCategoryId()));
        product.setOwner(userService.finById(productCreateDTO.getOwnerId()));

        productService.saveProduct(product);

        productImageLinks = productCreateDTO.getProductImages();
        for (String productImageLink: productImageLinks){
            productImage.setLink(productImageLink);
            productImage.setProduct(product);
            productImageService.save(productImage);
        }

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Product>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Product> updateProduct(@PathVariable("id") long id,@RequestBody ProductCreateDTO productCreateDTO ){
        Product currentProduct = productService.findById(id);
        currentProduct.setId(id);
        currentProduct.setName(productCreateDTO.getName());
        currentProduct.setApprovementStatus(approvementStatusService.findById(productCreateDTO.getApprovementStatusId()));
        currentProduct.setDescription(productCreateDTO.getDescription());
        currentProduct.setRegisterDate(productCreateDTO.getRegisterDate());
        currentProduct.setStartDate(productCreateDTO.getStartDate());
        currentProduct.setEndDate(productCreateDTO.getEndDate());
        currentProduct.setInitialPrice(productCreateDTO.getInitialPrice());
        currentProduct.setIncreaseAmount(productCreateDTO.getIncreaseAmount());
        currentProduct.setCategory(categoryService.findById(productCreateDTO.getCategoryId()));
        currentProduct.setOwner(userService.finById(productCreateDTO.getOwnerId()));
        productService.saveProduct(currentProduct);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Product>(headers, HttpStatus.CREATED);
    }


}
