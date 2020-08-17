package md4.bid_project.services;

import md4.bid_project.models.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();

    void save(Category category);

     Optional<Category> findById(Long categoryId);
}
