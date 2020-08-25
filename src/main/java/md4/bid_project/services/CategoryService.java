package md4.bid_project.services;

import md4.bid_project.models.Category;

import java.util.List;

public interface CategoryService {
    //Thành
    List<Category> findAll();
    //Thành
    void save(Category category);
    //Thành
     Category findById(Long categoryId);
//    Category findById(Long id);

}
