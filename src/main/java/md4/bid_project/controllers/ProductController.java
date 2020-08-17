package md4.bid_project.controllers;

import md4.bid_project.models.ApprovementStatus;
import md4.bid_project.models.Product;
import md4.bid_project.services.ApprovementStatusService;
import md4.bid_project.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private FavoriteProductService favoriteProductService;

    @GetMapping("/products/owner/{ownerId}")
    public ResponseEntity<List<Product>> getProductByOwnerId(@PathVariable(value = "ownerId") Long ownerId) {
        return ResponseEntity.ok(productService.findProductByOwnerId(ownerId));
    }

    // Create: Toàn
    // Lấy danh sách sản phẩm yêu thích
    @GetMapping(path = "/product/favorite/{userId}")
    public ResponseEntity<Page<FavoriteProduct>> getFavoriteProductsByUserId(@PathVariable(name = "userId") Long userId,
                                                                             Pageable pageable)
            throws ResourceNotFoundException {
        Page<FavoriteProduct> favoriteProducts = favoriteProductService.findByUserID(userId, pageable);
        if (favoriteProducts.getTotalPages() > 0)
            return ResponseEntity.ok(favoriteProducts);
        else
            throw new ResourceNotFoundException("No favorite product!");
    }

    // Create: Toàn
    // Thêm 1 sản phẩm vào danh sách yêu thích
    @PostMapping(path = "/product/favorite")
    public ResponseEntity<FavoriteProduct> createFavoriteProduct(@RequestBody FavoriteProduct favoriteProduct)
            throws ResourceNotFoundException {
        FavoriteProduct result = favoriteProductService.createFavoriteProduct(favoriteProduct);
        if (result != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        else
            throw new ResourceNotFoundException("Failed to create a favorite product!");
    }

    // Create: Toàn
    // Xóa một sản phẩm khỏi danh sách yêu thích
    @DeleteMapping(path = "/product/favorite/{favoriteProductId}")
    public ResponseEntity<Void> deleteFavoriteProduct(
            @PathVariable(name = "favoriteProductId") Long favoriteProductId) {
        favoriteProductService.deleteFavoriteProduct(favoriteProductId);
        return ResponseEntity.ok(null);
    }

    // Create: Toàn
    // Lấy danh sách sản phẩm đã được duyệt
    @GetMapping(path = "/product/approved/{userId}")
    public ResponseEntity<Page<Product>> getApprovedProductsByUserId(
            @PathVariable(name = "userId") Long userId, Pageable pageable)
            throws ResourceNotFoundException {
        Page<Product> products = productService.findApprovedProductsByUserId(userId, pageable);
        if (products.getContent().size() > 0)
            return ResponseEntity.ok(products);
        else
            throw new ResourceNotFoundException("No favorite product!");
    }

    // Create: Toàn
    // Lấy danh sách sản phẩm đang chờ duyệt
    @GetMapping(path = "/product/waiting/{userId}")
    public ResponseEntity<Page<Product>> getWaitingProductsByUserId(
            @PathVariable(name = "userId") Long userId, Pageable pageable)
            throws ResourceNotFoundException {
        Page<Product> products = productService.findWaitingProductsByUserId(userId, pageable);
        if (products.getContent().size() > 0)
            return ResponseEntity.ok(products);
        else
            throw new ResourceNotFoundException("No favorite product!");
    }
    private ApprovementStatusService approvementStatusService;

    //    Creator : Cường
    @GetMapping("/myProduct/{ownerId}")
    public ResponseEntity<Page<Product>> getProductByOwnerId(@PathVariable(value = "ownerId") Long ownerId,
                                                             @RequestParam(name = "productName",defaultValue = "") String productName,
                                                             @RequestParam(name = "approvementStatusName",defaultValue = "") String approvementStatusName,
                                                             @PageableDefault(value = 4) Pageable pageable) {
        Page<Product> productPage = productService.findProductByOwnerIdAndNameAndApprovementStatus(ownerId,productName,approvementStatusName,pageable);
        if (productPage.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productPage);
    }

    //    Creator : Cường
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
        Page<Product> productPage = productService.findProductByOwnerIdAndNameAndApprovementStatus(ownerId,productName,approvementStatusName,pageable);
        if (productPage.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productPage);
    }



}
