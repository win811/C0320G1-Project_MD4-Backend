package md4.bid_project.controllers;

import md4.bid_project.models.Category;
import md4.bid_project.models.Product;
import md4.bid_project.models.User;
import md4.bid_project.models.dto.ProductCreateDTO;
import md4.bid_project.services.CategoryService;
import md4.bid_project.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")

public class ProductController {
    @Autowired
    public ProductService productService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> listProduct() {
        List<Product> pageProduct = productService.findAllProduct();


        if (pageProduct.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Product>>(pageProduct, HttpStatus.OK);
    }

    @GetMapping("products/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "productId") Long productID) {
        Product productObj = productService.findProductById(productID);
        return new ResponseEntity<Product>(productObj, HttpStatus.OK);
    }

    @GetMapping("/products/owner/{ownerId}")
    public ResponseEntity<List<Product>> getProductByOwnerId(@PathVariable(value = "ownerId") Long ownerId) {
        return ResponseEntity.ok(productService.findProductByOwnerId(ownerId));
    }

//    @PostMapping("/products")
//    public ResponseEntity<Void> createProduct(@RequestBody Product product){
//        System.out.println(product);
//        productService.saveProduct(product);
//        return new ResponseEntity<Void>(HttpStatus.CREATED);
//    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody ProductCreateDTO productCreateDTO) {
        System.out.println(productCreateDTO);
        Product product = productService.saveProduct(productCreateDTO);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/products")
    public ResponseEntity<Product> updateProduct(@RequestBody ProductCreateDTO productCreateDTO) {
        System.out.println(productCreateDTO);
        Product product = productService.saveProduct(productCreateDTO);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @GetMapping("/categorys")
    public ResponseEntity<List<Category>> listCategory() {
        List<Category> listCategory = categoryService.findAllCategory();
        if (listCategory.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Category>>(listCategory, HttpStatus.OK);
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<User> getOwnerById(@PathVariable(value = "ownerId") Long ownerId) {
        User ownerObj = productService.findOwnerById(ownerId);
        return new ResponseEntity<User>(ownerObj, HttpStatus.OK);
    }

}

