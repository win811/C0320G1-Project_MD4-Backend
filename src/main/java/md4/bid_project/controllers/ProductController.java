package md4.bid_project.controllers;

import md4.bid_project.models.Product;
import md4.bid_project.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products/owner/{ownerId}")
    public ResponseEntity<List<Product>> getProductByOwnerId(@PathVariable(value = "ownerId") Long ownerId) {
        return ResponseEntity.ok(productService.findProductByOwnerId(ownerId));
    }

}
