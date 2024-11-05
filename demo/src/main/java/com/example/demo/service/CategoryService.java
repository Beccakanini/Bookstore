package com.example.demo.service;

import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // Get all categories
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    // Find a category by ID
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    // Save a new category
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    // Delete a category by ID
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
