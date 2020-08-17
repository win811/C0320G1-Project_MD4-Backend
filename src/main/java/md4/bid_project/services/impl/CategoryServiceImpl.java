package md4.bid_project.services.impl;

import md4.bid_project.models.Category;
import md4.bid_project.repositories.CategoryRepository;
import md4.bid_project.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
@Autowired
private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public Optional<Category> findById(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }
}
