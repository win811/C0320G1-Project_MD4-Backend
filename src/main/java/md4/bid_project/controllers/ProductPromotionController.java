package md4.bid_project.controllers;


import md4.bid_project.exception.ResourceNotFoundException;
import md4.bid_project.models.Product;
import md4.bid_project.models.ProductPromotion;
import md4.bid_project.models.dto.ProductPromotionDto;
import md4.bid_project.services.ProductPromotionService;
import md4.bid_project.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/productPromotion")
public class ProductPromotionController {
    @Autowired
    private ProductPromotionService productPromotionService;
    @Autowired
    private ProductService productService;

    @GetMapping("/productPromotions")
    public List<ProductPromotion> getAllProductPromotion() {
        return productPromotionService.findAll();
    }

    @GetMapping("/productPromotions/{id}")
    public ResponseEntity<ProductPromotion> getProductPromotionsById(@PathVariable(value = "id") Long productPromotionId)
            throws ResourceNotFoundException {
        ProductPromotion productPromotion = productPromotionService.findById(productPromotionId);
        return ResponseEntity.ok().body(productPromotion);
    }

    @GetMapping("/productPromotio/{id}")
    public ResponseEntity<ProductPromotionDto> getProductPromotionsByIdaaaa(@PathVariable(value = "id") Long productPromotionId)
            throws ResourceNotFoundException {
        ProductPromotionDto productPromotion = new ProductPromotionDto();
        return ResponseEntity.ok().body(productPromotion);
    }

    @GetMapping("/productPromotionsDto/{id}")
    public ResponseEntity<ProductPromotionDto> getProductPromotionDtoById(@PathVariable(value = "id") Long productPromotionId)
            throws ResourceNotFoundException {
        ProductPromotionDto productPromotionDto = productPromotionService.findProductPromotionDto(productPromotionId);
        return ResponseEntity.ok().body(productPromotionDto);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Long productId)
            throws ResourceNotFoundException {
        Product product = productService.findById(productId);
        return ResponseEntity.ok().body(product);
    }

    @GetMapping("/products")
    public List<Product> getAllProduct() {
        return productService.findAll();
    }


    @PostMapping("/productPromotions")
    public ProductPromotion createProductPromotions(@RequestBody ProductPromotionDto productPromotionDto) {
        return productPromotionService.save(productPromotionDto);
    }

    @PutMapping("/productPromotions/{id}")
    public ResponseEntity<ProductPromotion> updateProductPromotions(@PathVariable(value = "id") Long productPromotionsId,
                                                                    @RequestBody ProductPromotionDto productPromotionsDetail) throws ResourceNotFoundException {
        ProductPromotion productPromotion = productPromotionService.findById(productPromotionsId);
        productPromotion.setContent(productPromotionsDetail.getContent());
        productPromotion.setStartDate(productPromotionsDetail.getStartDate());
        productPromotion.setEndDate(productPromotionsDetail.getEndDate());
        productPromotion.setPercent(productPromotionsDetail.getPercent());
        productPromotion.setPrice(productPromotionsDetail.getPrice());
        productPromotion.setProduct(productService.findById(productPromotionsDetail.getIdProduct()));
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

