package com.example.Productos.service;

import com.example.Productos.exception.ResourceNotFoundException;
import com.example.Productos.model.Category;
import com.example.Productos.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository repoCategory;

    public CategoryService(CategoryRepository repoCategory) {
        this.repoCategory = repoCategory;
    }

    public List<Category> getAll() {
        return repoCategory.findAll();
    }

    public Category findById(Long id) {
        return repoCategory.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category Not Found with ID: " + id)
        );
    }

    public Category save(Category category) {
        return repoCategory.save(category);
    }

    public Category update(Long id, Category category) {
        Category categoryDb = findById(id);
        categoryDb.setName(category.getName());
        return repoCategory.save(categoryDb);
    }

    public boolean delete(Long id) {
        if (!repoCategory.existsById(id)) {
            return false;
        }
        repoCategory.deleteById(id);
        return true;
    }
}