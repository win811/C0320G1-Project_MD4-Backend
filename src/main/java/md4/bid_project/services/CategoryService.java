package md4.bid_project.services;

import md4.bid_project.models.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    //Thành
    List<Category> findAll();
    //Thành
    void save(Category category);
    //Thành
     Optional<Category> findById(Long categoryId);
}
