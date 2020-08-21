package md4.bid_project.controllers;

import md4.bid_project.models.ProductPromotion;
import md4.bid_project.services.ProductPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/productPromotion")
public class ProductPromotionController {
    @Autowired
    private ProductPromotionService productPromotionService;

    @GetMapping("/productPromotions")
    public List<ProductPromotion> getAllProductPromotion() {
        return productPromotionService.findAll();
    }
    @GetMapping("/search")
    public List<ProductPromotion> search(  @RequestParam(name = "content",required = false) String content,
                                            @RequestParam(name = "saleDate",required = false) String saleDate,
                                            @RequestParam(name = "endOfEvent",defaultValue = "1900-1-1") String endOfEvent,
                                            @RequestParam(name = "percent",required = false) String percent
    ) {
        return productPromotionService.search(content, saleDate, endOfEvent, percent);
    }
}
