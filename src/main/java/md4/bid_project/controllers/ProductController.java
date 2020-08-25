package md4.bid_project.controllers;

import md4.bid_project.exception.ResourceNotFoundException;
import md4.bid_project.exception.ViolatedException;
import md4.bid_project.models.ApprovementStatus;
import md4.bid_project.models.FavoriteProduct;
import md4.bid_project.models.Product;
import md4.bid_project.models.ProductImage;
import md4.bid_project.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


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
    private AuctionService auctionService;

    @Autowired
    private ApprovementStatusService approvementStatusService;

    @Autowired
    private FavoriteProductService favoriteProductService;

    // Creator : Cường
    @GetMapping("/myProduct/{ownerId}")
    public ResponseEntity<Page<Product>> getProductByOwnerId(@PathVariable(value = "ownerId") Long ownerId,
                                                             @RequestParam(name = "productName", defaultValue = "") String productName,
                                                             @RequestParam(name = "approvementStatusName", defaultValue = "") String approvementStatusName,
                                                             @RequestParam(name = "page",defaultValue = "0") int page) {

        Page<Product> productPage = productService.findProductByOwnerIdAndNameAndApprovementStatus(ownerId, productName, approvementStatusName, page);
        return ResponseEntity.ok(productPage);
    }

    //Thành
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.findAllProduct();
    }

    // Creator : Cường
    @PutMapping("/myProduct/cancel/{ownerId}")
    public ResponseEntity<Page<Product>> cancelProductApprovementStatus(@PathVariable(value = "ownerId") Long ownerId,
                                                                        @RequestParam(name = "productName", defaultValue = "") String productName,
                                                                        @RequestParam(name = "approvementStatusName", defaultValue = "") String approvementStatusName,
                                                                        @RequestParam(name = "cancelProductId", defaultValue = "0") Long cancelProductId,
                                                                        @RequestParam(name = "page",defaultValue = "0") int page) {
        if (cancelProductId != 0) {
            Product product = productService.findById(cancelProductId);
            ApprovementStatus approvementStatus = approvementStatusService.findByName("đã hủy");
            product.setApprovementStatus(approvementStatus);
            productService.saveProduct(product);
        }

        Page<Product> productPage = productService.findProductByOwnerIdAndNameAndApprovementStatus(ownerId, productName, approvementStatusName, page);
        return ResponseEntity.ok(productPage);
    }

//    @GetMapping("/products/owner/{ownerId}")
//    public ResponseEntity<List<Product>> getProductByOwnerId(@PathVariable(value = "ownerId") Long ownerId) {
//        return ResponseEntity.ok(productService.findProductByOwnerId(ownerId));
//    }

    // Created by: Toàn
    // Lấy danh sách sản phẩm yêu thích
    @GetMapping(path = "/product/favorite")
    public ResponseEntity<Page<FavoriteProduct>> getFavoriteProductsByUserId(@RequestParam(name = "userId") Long userId,
                                                                             @PageableDefault(value = 8) Pageable pageable)
            throws ResourceNotFoundException {
        Page<FavoriteProduct> favoriteProducts = favoriteProductService.findByUserID(userId, pageable);
        if (favoriteProducts.getTotalPages() > 0) {
            return ResponseEntity.ok(favoriteProducts);
        } else
            throw new ResourceNotFoundException("No favorite product!");
    }

    // Created by: Toàn
    // Thêm 1 sản phẩm vào danh sách yêu thích
    @PostMapping(path = "/product/favorite")
    public ResponseEntity<FavoriteProduct> createFavoriteProduct(@RequestBody FavoriteProduct favoriteProduct,
                                                                 Authentication authentication)
            throws ResourceNotFoundException {
        System.out.println("createFavoriteProduct -" + authentication.getName());
        FavoriteProduct result = favoriteProductService.createFavoriteProduct(favoriteProduct);
        if (result != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        else
            throw new ResourceNotFoundException("Failed to create a favorite product!");
    }

    // Created by: Toàn
    // Xóa một sản phẩm khỏi danh sách yêu thích
    @DeleteMapping(path = "/product/favorite/{favoriteProductId}")
    public ResponseEntity<Void> deleteFavoriteProduct(
            @PathVariable(name = "favoriteProductId") Long favoriteProductId) {
        favoriteProductService.deleteFavoriteProduct(favoriteProductId);
        return ResponseEntity.ok().body(null);
    }

    // Created by: Toàn
    // Lấy danh sách sản phẩm đã được duyệt
    @GetMapping(path = "/product/approved")
    public ResponseEntity<Page<Product>> getApprovedProductsByUserId(@RequestParam(name = "userId") Long userId,
                                                                     Pageable pageable) throws ResourceNotFoundException {
        Page<Product> products = productService.findApprovedProductsByUserId(userId, pageable);
        if (products.getContent().size() > 0)
            return ResponseEntity.ok(products);
        else
            throw new ResourceNotFoundException("No favorite product!");
    }

    // Created by: Toàn
    // Lấy danh sách sản phẩm đang chờ duyệt
    @GetMapping(path = "/product/waiting")
    public ResponseEntity<Page<Product>> getWaitingProductsByUserId(@RequestParam(name = "userId") Long userId,
                                                                    Pageable pageable) throws ResourceNotFoundException {
        Page<Product> products = productService.findWaitingProductsByUserId(userId, pageable);
        if (products.getContent().size() > 0)
            return ResponseEntity.ok(products);
        else
            throw new ResourceNotFoundException("No favorite product!");
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
        if (bindingResult.hasErrors()) {
            throw new ViolatedException(bindingResult);
        }
        productService.save(product);
        List<ProductImage> productImages = new ArrayList<>();
        productImages = product.getProductImages();
        for (ProductImage productImage : productImages) {
            productImage.setProduct(product);
        }
        productImageService.saveAll(productImages);
        return ResponseEntity.ok().body(product);
    }
}
