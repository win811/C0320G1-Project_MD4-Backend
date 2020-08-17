package md4.bid_project.controllers;

import md4.bid_project.models.ApprovementStatus;
import md4.bid_project.models.Auction;
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
    private ProductService productService;

    @GetMapping("/products/owner/{ownerId}")
    public ResponseEntity<List<Product>> getProductByOwnerId(@PathVariable(value = "ownerId") Long ownerId) {
        return ResponseEntity.ok(productService.findProductByOwnerId(ownerId));
    }

    @GetMapping("/product/list")
    public ResponseEntity<List<Product>> listAllProduct(){
        List<Product> products = productService.getAllProduct();
        if(products.isEmpty()){
            return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }
    @GetMapping("/admin/approvement/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        Product product = productService.getProductById(id);
        if (product == null) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }
    @GetMapping("admin/approvement/approve/{id}")
    public ResponseEntity<Product> approvementProduct(@PathVariable Long id){
        Product product = productService.getProductById(id);
        if (product == null) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
        ApprovementStatus approve = new ApprovementStatus();
        approve.setId(2L);
        productService.approvementProduct(product, approve);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("admin/unApprovement/approve/")
    public ResponseEntity<Product> unApprovementProduct(@PathVariable Product product){
        productService.unApprovementProduct(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
