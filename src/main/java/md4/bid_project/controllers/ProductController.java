package md4.bid_project.controllers;

import md4.bid_project.models.ApprovementStatus;
import md4.bid_project.models.Auction;
import md4.bid_project.models.ApprovementStatus;
import md4.bid_project.models.Product;
import md4.bid_project.services.ApprovementStatusService;
import md4.bid_project.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ApprovementStatusService approvementStatusService;

    //    Cường
    @GetMapping("/myProduct/{ownerId}")
    public ResponseEntity<Page<Product>> getProductByOwnerId(@PathVariable(value = "ownerId") Long ownerId,
                                                             @RequestParam(name = "productName", defaultValue = "") String productName,
                                                             @RequestParam(name = "approvementStatusName", defaultValue = "") String approvementStatusName,
                                                             @PageableDefault(value = 4) Pageable pageable) {
        Page<Product> productPage = productService.findProductByOwnerIdAndNameAndApprovementStatus(ownerId, productName, approvementStatusName, pageable);
        return ResponseEntity.ok(productPage);
    }

    //    Cường
    @PutMapping("/myProduct/cancel/{ownerId}")
    public ResponseEntity<Page<Product>> cancelProductApprovementStatus(@PathVariable(value = "ownerId") Long ownerId,
                                                                        @RequestParam(name = "productName", defaultValue = "") String productName,
                                                                        @RequestParam(name = "approvementStatusName", defaultValue = "") String approvementStatusName,
                                                                        @RequestParam(name = "cancelProductId", defaultValue = "0") Long cancelProductId,
                                                                        @PageableDefault(value = 4) Pageable pageable) {
        if (cancelProductId != 0) {
            Product product = productService.findById(cancelProductId);
            ApprovementStatus approvementStatus = approvementStatusService.findByName("đã hủy");
            product.setApprovementStatus(approvementStatus);
            productService.save(product);
        }
        Page<Product> pageProduct = productService.findProductByOwnerIdAndNameAndApprovementStatus(ownerId, productName, approvementStatusName, pageable);
        return ResponseEntity.ok(pageProduct);
    }


    //Thành Long
    @GetMapping("/product/list")
    public ResponseEntity<List<Product>> listAllProduct() {
        List<Product> products = productService.getAllProduct();
        if (products.isEmpty()) {
            return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    //Thành Long
    @GetMapping("/admin/approvement/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    //Thành Long
    @GetMapping("admin/approvement/approve/{id}")
    public ResponseEntity<Product> approvementProduct(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
        ApprovementStatus approve = new ApprovementStatus();
        approve.setId(2L);
        productService.approvementProduct(product, approve);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Thành Long
    @GetMapping("admin/unApprovement/approve/")
    public ResponseEntity<Product> unApprovementProduct(@PathVariable Product product) {
        productService.unApprovementProduct(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
