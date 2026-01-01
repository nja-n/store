package com.aeither.store.assests.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.aeither.store.assests.domain.model.Category;
import com.aeither.store.assests.infrastructure.repository.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll().stream()
                .filter(category -> !"DELETED".equals(category.getStatus()))
                .toList();
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        Category category = findById(id);
        if (category != null) {
            category.setStatus("DELETED");
            categoryRepository.save(category);
        }
    }
}
