package md4.bid_project.controllers;


import md4.bid_project.exception.ResourceNotFoundException;
import md4.bid_project.models.Category;
import md4.bid_project.models.Product;
import md4.bid_project.repositories.CategoryRepository;
import md4.bid_project.repositories.ProductRepository;
import md4.bid_project.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public List<Category> getAllProducts() {
        return categoryService.findAll();
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable(value = "id") Long categoryId)
            throws ResourceNotFoundException {
        Category category = categoryService.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found for this id :: " + categoryId));
        return ResponseEntity.ok().body(category);
    }

    @PostMapping("/categories")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
         categoryService.save(category);
        return ResponseEntity.ok(new Category());
    }
}
