package md4.bid_project.controllers;
import md4.bid_project.exception.ResourceNotFoundException;
import md4.bid_project.models.Product;
import md4.bid_project.models.ProductPromotion;
import md4.bid_project.models.User;
import md4.bid_project.models.dto.ProductPromotionDto;
import md4.bid_project.repositories.ProductRepository;
import md4.bid_project.repositories.UserRepository;
import md4.bid_project.services.ProductPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class ProductPromotionController {
    @Autowired
    private ProductPromotionService productPromotionService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/productPromotions")
    public List<ProductPromotion> getAllProductPromotion() {
        return productPromotionService.findAll();
    }
    //creator: đức thông
    @GetMapping("/productPromotions/{id}")
    public ResponseEntity<ProductPromotion> getProductPromotionsById(@PathVariable(value = "id") Long productPromotionId)
            throws ResourceNotFoundException {
        ProductPromotion productPromotion = productPromotionService.findById(productPromotionId);
        return ResponseEntity.ok().body(productPromotion);
    }
    //creator: đức thông
    @GetMapping("/productPromotionsDto/{id}")
    public ResponseEntity<ProductPromotionDto> getProductPromotionDtoById(@PathVariable(value = "id") Long productPromotionId)
            throws ResourceNotFoundException {
        ProductPromotionDto productPromotionDto = productPromotionService.findProductPromotionDto(productPromotionId);
        return ResponseEntity.ok().body(productPromotionDto);
    }
    //creator: đức thông
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Long productId)
            throws ResourceNotFoundException {
        Product product = productRepository.findById(productId).orElse(null);
        return ResponseEntity.ok().body(product);
    }
    //creator: đức thông
    @GetMapping("/products")
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }
    @GetMapping("/users")
    public List<User> getListUser(){
        return userRepository.findAll();
    }
    //creator: đức thông
    @PostMapping("/productPromotions")
    public ProductPromotion createProductPromotions(@RequestBody ProductPromotionDto productPromotionDto) {
        return productPromotionService.save(productPromotionDto);
    }
    //creator: đức thông
    @PutMapping("/productPromotions/{id}")
    public ResponseEntity<ProductPromotion> updateProductPromotions(@PathVariable(value = "id") Long productPromotionsId,
                                                                    @RequestBody ProductPromotionDto productPromotionsDetail) throws ResourceNotFoundException {
        ProductPromotion productPromotion = productPromotionService.findById(productPromotionsId);
        productPromotion.setContent(productPromotionsDetail.getContent());
        productPromotion.setStartDate(productPromotionsDetail.getStartDate());
        productPromotion.setEndDate(productPromotionsDetail.getEndDate());
        productPromotion.setPercent(productPromotionsDetail.getPercent());
        productPromotion.setPrice(productPromotionsDetail.getPrice());
        productPromotion.setProduct(productRepository.findById(productPromotionsDetail.getIdProduct()).orElse(null));
        productPromotionService.update(productPromotion);
        return ResponseEntity.ok().body(productPromotion);
    }

    @DeleteMapping("/productPromotions/{id}")
    public Map<String, Boolean> deleteProductPromotion(@PathVariable(value = "id") Long productPromotionsId)
            throws ResourceNotFoundException{
        ProductPromotion productPromotion= productPromotionService.findById(productPromotionsId);
        productPromotionService.delete(productPromotion);
        Map<String, Boolean> response=new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
//        @DeleteMapping("/employees/{id}")
}