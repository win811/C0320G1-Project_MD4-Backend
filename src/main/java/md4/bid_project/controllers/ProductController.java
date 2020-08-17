package md4.bid_project.controllers;

import md4.bid_project.models.Product;
import md4.bid_project.models.ProductImage;
import md4.bid_project.services.ProductImageService;
import md4.bid_project.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductImageService productImageService;


    @GetMapping("/product")
    public ResponseEntity<List<Product>> showListProduct(){
        List<Product> products = productService.getAllProduct();
        return ResponseEntity.ok(products);
    }
    @GetMapping("/product/image")
    public ResponseEntity<List<ProductImage>> showListProductImage(){
        List<ProductImage> productImages = productImageService.getAllProductImage();
        return ResponseEntity.ok(productImages);
    }
}
