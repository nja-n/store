package com.aeither.store.assests.application.provider;

import com.aeither.store.common.application.SetupDataProvider;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import com.aeither.store.assests.application.SubCategoryService;
import com.aeither.store.assests.domain.model.SubCategory;

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
