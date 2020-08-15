package md4.bid_project.controllers;

import md4.bid_project.models.Product;
import md4.bid_project.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")

public class ProductController {
    @Autowired
    public ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> listProduct() {
        List<Product> pageProduct = productService.findAllProduct();


        if (pageProduct.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Product>>(pageProduct, HttpStatus.OK);
    }
    @GetMapping("/products/owner/{ownerId}")
    public ResponseEntity<List<Product>> getProductByOwnerId(@PathVariable(value = "ownerId") Long ownerId) {
        return ResponseEntity.ok(productService.findProductByOwnerId(ownerId));
    }

    @PostMapping("/products")
    public ResponseEntity<Void> createProduct(@RequestBody Product product){
        productService.saveProduct(product);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
    @PutMapping("/products")
    public ResponseEntity<Void> updateProduct(@RequestBody Product product){
        productService.saveProduct(product);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
