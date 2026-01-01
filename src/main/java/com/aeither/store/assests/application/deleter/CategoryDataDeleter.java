package com.aeither.store.assests.application.deleter;

import com.aeither.store.common.application.SetupDataDeleter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import com.aeither.store.assests.application.CategoryService;

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
