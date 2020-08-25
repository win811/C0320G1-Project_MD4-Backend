package md4.bid_project.controllers;

import md4.bid_project.models.ProductImage;
import md4.bid_project.services.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/productImages")
public class ProductImageController {
    @Autowired
    private ProductImageService productImageService;

    @GetMapping("")
    public ResponseEntity<List<ProductImage>> listAllProductImage() {
        List<ProductImage> productImages = productImageService.findAll();
        if(productImages.isEmpty()){
            return new ResponseEntity<List<ProductImage>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<ProductImage>>(productImages,HttpStatus.OK);
    }
}
