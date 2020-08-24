package md4.bid_project.controllers;

import md4.bid_project.models.Category;
import md4.bid_project.models.ApprovementStatus;
import md4.bid_project.models.Product;
import md4.bid_project.models.User;
import md4.bid_project.models.dto.ProductCreateDTO;
import md4.bid_project.services.CategoryService;
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

    @Autowired
    private ApprovementStatusService approvementStatusService;

    //    Cường
    @GetMapping("/myProduct/{ownerId}")
    public ResponseEntity<Page<Product>> getProductByOwnerId(@PathVariable(value = "ownerId") Long ownerId,
                                                             @RequestParam(name = "productName",defaultValue = "") String productName,
                                                             @RequestParam(name = "approvementStatusName",defaultValue = "") String approvementStatusName,
                                                             @PageableDefault(value = 4) Pageable pageable) {
        Page<Product> productPage = productService.findProductByOwnerIdAndNameAndApprovementStatus(ownerId,productName,approvementStatusName,pageable);
        return ResponseEntity.ok(productPage);
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


    // Creator : Nguyen Phi Son

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody ProductCreateDTO productCreateDTO) {
        System.out.println(productCreateDTO);
        Product product = productService.saveProduct(productCreateDTO);

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Void> updateProduct(@RequestBody ProductCreateDTO productCreateDTO, @PathVariable(value = "id") Long id) {
        System.out.println("check data");
        System.out.println(productCreateDTO);
        productService.updateProduct(productCreateDTO,id);
        System.out.println("done yet ?");
        return new ResponseEntity<>( HttpStatus.CREATED);
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


    //Thành Long
    @GetMapping("/product/list")
    public ResponseEntity<List<Product>> listAllProduct() {
        List<Product> products = productService.getAllProduct();
        if (products.isEmpty()) {
            return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

}

