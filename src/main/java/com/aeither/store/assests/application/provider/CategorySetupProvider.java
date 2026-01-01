package com.aeither.store.assests.application.provider;

import com.aeither.store.common.application.SetupDataProvider;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import com.aeither.store.assests.application.CategoryService;
import com.aeither.store.assests.domain.model.Category;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategorySetupProvider implements SetupDataProvider {

    private final CategoryService categoryService;

    @Override
    public String getKey() {
        return "categories";
    }

    @Override
    public Object getInitialState() {
        return new Category();
    }

    @Override
    public String getInitialStateKey() {
        return "category";
    }

    @Override
    public List<?> getList() {
        return categoryService.findAll();
    }
}
