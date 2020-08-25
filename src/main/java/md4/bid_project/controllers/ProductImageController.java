package md4.bid_project.controllers;

import md4.bid_project.exception.ResourceNotFoundException;
import md4.bid_project.models.ProductImage;
import md4.bid_project.services.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class ProductImageController {
    //Thành
    @Autowired
    private ProductImageService productImageService;
    //Thành
    @GetMapping("/productimages")
    public List<ProductImage> getAllProductImages() {
        return productImageService.findAll();
    }

    //Thành
    @GetMapping("/productimages/{id}")
    public ResponseEntity<ProductImage> getProductImageById(@PathVariable(value = "id") Long productImageId)
            throws ResourceNotFoundException {
        ProductImage productImage = productImageService.findById(productImageId)
                .orElseThrow(() -> new ResourceNotFoundException("ProductImage not found for this id :: " + productImageId));
        return ResponseEntity.ok().body(productImage);
    }
    //Thành
    @PostMapping("/productimages")
    public ResponseEntity<ProductImage> createProduct(@RequestBody ProductImage productImage) {
        System.out.println(productImage);
        productImageService.save(productImage);
        return ResponseEntity.ok(new ProductImage());
    }
     // Tu
    @GetMapping("productImages")
    public ResponseEntity<List<ProductImage>> listAllProductImage() {
        List<ProductImage> productImages = productImageService.findAll();
        if(productImages.isEmpty()){
            return new ResponseEntity<List<ProductImage>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<ProductImage>>(productImages,HttpStatus.OK);
    }
}
