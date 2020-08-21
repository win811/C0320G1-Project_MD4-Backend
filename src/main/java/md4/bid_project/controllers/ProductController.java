package md4.bid_project.controllers;

import md4.bid_project.exception.ViolatedException;
import md4.bid_project.models.ApprovementStatus;
import md4.bid_project.models.Product;
import md4.bid_project.services.ApprovementStatusService;
import md4.bid_project.models.ProductImage;
import md4.bid_project.repositories.ProductImageRepository;
import md4.bid_project.services.ProductImageService;
import md4.bid_project.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import md4.bid_project.exception.ResourceNotFoundException;
import md4.bid_project.repositories.ProductRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class ProductController {
    //Thành
    @Autowired
    private ProductImageService productImageService;
    //Thành
    @Autowired
    private ProductService productService;

    @Autowired
    private ApprovementStatusService approvementStatusService;

    //    Cường
    @GetMapping("/myProduct/{ownerId}")
    public ResponseEntity<Page<Product>> getProductByOwnerId(@PathVariable(value = "ownerId") Long ownerId,
                                                             @RequestParam(name = "productName",defaultValue = "") String productName,
                                                             @RequestParam(name = "approvementStatusName",defaultValue = "") String approvementStatusName,
                                                             @PageableDefault(value = 4) Pageable pageable) {
        Page<Product> productPage = productService.findProductByOwnerIdAndNameAndApprovementStatus(ownerId,productName,approvementStatusName,pageable);
        return ResponseEntity.ok(productPage);}
    //Thành
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.findAllProduct();
    }



    @GetMapping("/products/owner/{ownerId}")
    public ResponseEntity<List<Product>> getProductByOwnerId(@PathVariable(value = "ownerId") Long ownerId) {
        return ResponseEntity.ok(productService.findProductByOwnerId(ownerId));
    }

    //    Cường
    @PutMapping("/myProduct/cancel/{ownerId}")
    public ResponseEntity<Page<Product>> cancelProductApprovementStatus (@PathVariable(value = "ownerId") Long ownerId,
                                                                         @RequestParam(name = "productName",defaultValue = "") String productName,
                                                                         @RequestParam(name = "approvementStatusName",defaultValue = "") String approvementStatusName,
                                                                         @RequestParam(name = "cancelProductId", defaultValue = "0") Long cancelProductId,
                                                                         @PageableDefault(value = 4) Pageable pageable) {
        if (cancelProductId != 0) {
            Product product = productService.findById(cancelProductId);
            ApprovementStatus approvementStatus = approvementStatusService.findByName("đã hủy");
            product.setApprovementStatus(approvementStatus);
            productService.save(product);
        }
        Page<Product> pageProduct = productService.findProductByOwnerIdAndNameAndApprovementStatus(ownerId,productName,approvementStatusName,pageable);
        return ResponseEntity.ok(pageProduct);
    }

    //Thành
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Long productId)
            throws ResourceNotFoundException {
        Product product = productService.findProductById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + productId));
        return ResponseEntity.ok().body(product);
    }
    //Thành
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product,
                                                 BindingResult bindingResult) throws ViolatedException {
        if (bindingResult.hasErrors()){
            throw new ViolatedException(bindingResult);
        }
        productService.save(product);
        List<ProductImage> productImages = new ArrayList<>();
        productImages = product.getProductImageList();
        for (ProductImage productImage: productImages) {
            productImage.setProduct(product);
        }
        productImageService.saveAll(productImages);
        return ResponseEntity.ok().body(product);
    }
}
