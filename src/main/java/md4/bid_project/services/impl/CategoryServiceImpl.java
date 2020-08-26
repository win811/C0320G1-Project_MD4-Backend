package md4.bid_project.services.impl;

import md4.bid_project.models.Category;
import md4.bid_project.repositories.CategoryRepository;
import md4.bid_project.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {
@Autowired
private CategoryRepository categoryRepository;
    //Thành
    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
    //Thành
    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }
    //Thành
    @Override
    public Category findById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }
//    @Override
//    public Category findById(Long id) {
//        return categoryRepository.findById(id).orElse(null);
//    }
}
