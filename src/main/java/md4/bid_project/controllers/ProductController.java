package md4.bid_project.controllers;

import md4.bid_project.exception.ResourceNotFoundException;
import md4.bid_project.models.ApprovementStatus;
import md4.bid_project.models.Product;
import md4.bid_project.models.dto.ProductSearchField;
import md4.bid_project.services.ApprovementStatusService;
import md4.bid_project.services.AuctionService;
import md4.bid_project.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.util.resources.cldr.ext.LocaleNames_en_GB;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private AuctionService auctionService;

    @Autowired
    private ApprovementStatusService approvementStatusService;

    //    Creator : Cường
    @GetMapping("/myProduct/{ownerId}")
    public ResponseEntity<Page<Product>> getProductByOwnerId(@PathVariable(value = "ownerId") Long ownerId,
                                                             @RequestParam(name = "productName", defaultValue = "") String productName,
                                                             @RequestParam(name = "approvementStatusName", defaultValue = "") String approvementStatusName,
                                                             @PageableDefault(value = 4) Pageable pageable) {

        Page<Product> productPage = productService.findProductByOwnerIdAndNameAndApprovementStatus(ownerId,productName,approvementStatusName,pageable);
        if (productPage.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productPage);
    }

    //    Creator : Cường
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

        Page<Product> productPage = productService.findProductByOwnerIdAndNameAndApprovementStatus(ownerId,productName,approvementStatusName,pageable);
        if (productPage.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productPage);
    }


    //Thành Long
    //Hiển thị list sản phẩm
    @GetMapping("/product/list")
    public ResponseEntity<Page<Product>> getAllProduct(@PageableDefault(value = 5) Pageable pageable) {
        Page<Product> products = productService.findAllProduct(pageable);
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/product/list/search")
    public ResponseEntity<Page<Product>> search(@RequestBody ProductSearchField searchField) {
        Specification<Product> specs = productService.getFilter(searchField);
        Page<Product> products;
        products =  productService.findCustomerByCriteria(specs, 0);
        return ResponseEntity.ok(products);
    }


    //Thành Long
    //Hiển thị chi tiết sản phẩm để duyệt
    @GetMapping("/admin/approvement/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    //Thành Long
    //Duyệt sản phẩm
    @GetMapping("admin/approvement/approve/{id}")
    public ResponseEntity<Product> approvementProduct(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
        if (product.getApprovementStatus().getId()==2) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
        ApprovementStatus approve = new ApprovementStatus();
        approve.setId(2L);
        productService.approvementProduct(product, approve);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Thành Long
    //Không duyệt sản phẩm
    @PutMapping("admin/approvement/unApprove")
    public ResponseEntity<Product> unApprovementProduct(@RequestBody Product requestBody) {
        Product product = productService.findById(requestBody.getId());
        if (product == null) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
        product.setDescription(requestBody.getDescription());
        ApprovementStatus unApprove = new ApprovementStatus();
        unApprove.setId(3L);
        productService.unApprovementProduct(product,unApprove);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/product/list/delete")
    public Map<String, Boolean> deleteProducts(@RequestBody Map<String, Long[]> requestBody) {
        Long[] ids = requestBody.get("ids").clone();
        Map<String, Boolean> response = new HashMap<>();
        for(Long id : ids) {
            Product product = productService.findById(id);
                productService.deleteProduct(product);
                response.put("deleted " + id, Boolean.TRUE);
            }
        return response;
    }

}
