package md4.bid_project.controllers;

import md4.bid_project.models.Product;
import md4.bid_project.models.ProductImage;
import md4.bid_project.repositories.ProductImageRepository;
import md4.bid_project.services.ProductImageService;
import md4.bid_project.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import md4.bid_project.exception.ResourceNotFoundException;
import md4.bid_project.repositories.ProductRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class ProductController {
    @Autowired
    private ProductImageService productImageService;

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.findAllProduct();
    }



    @GetMapping("/products/owner/{ownerId}")
    public ResponseEntity<List<Product>> getProductByOwnerId(@PathVariable(value = "ownerId") Long ownerId) {
        return ResponseEntity.ok(productService.findProductByOwnerId(ownerId));
    }
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Long productId)
            throws ResourceNotFoundException {
        Product product = productService.findProductById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + productId));
        return ResponseEntity.ok().body(product);
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        System.out.println(product);
        productService.save(product);
        List<ProductImage> productImages = new ArrayList<>();
        productImages = product.getProductImageList();
        for (ProductImage productImage: productImages) {
            productImage.setProduct(product);
        }
        productImageService.saveAll(productImages);
        return ResponseEntity.ok(new Product());
    }
}
