package md4.bid_project.services;

import md4.bid_project.models.Category;
import md4.bid_project.models.Product;

import java.util.List;

public interface CategoryService {
    Category findById(Long id);

    List<Category> findAll();
}
