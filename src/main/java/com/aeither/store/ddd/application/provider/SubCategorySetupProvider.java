package com.aeither.store.ddd.application.provider;

import com.aeither.store.ddd.application.SubCategoryService;
import com.aeither.store.ddd.domain.model.SubCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SubCategorySetupProvider implements SetupDataProvider {

    private final SubCategoryService subCategoryService;

    @Override
    public String getKey() {
        return "subCategories";
    }

    @Override
    public Object getInitialState() {
        return new SubCategory();
    }

    @Override
    public String getInitialStateKey() {
        return "subCategory";
    }

    @Override
    public List<?> getList() {
        return subCategoryService.findAll();
    }
}
