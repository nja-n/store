package com.aeither.store.ddd.application.deleter;

import com.aeither.store.ddd.application.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryDataDeleter implements SetupDataDeleter {

    private final CategoryService categoryService;

    @Override
    public String getDeleteKey() {
        return "category";
    }

    @Override
    public void delete(Long id) {
        categoryService.delete(id);
    }
}
