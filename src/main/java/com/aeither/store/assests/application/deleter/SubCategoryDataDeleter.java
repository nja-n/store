package com.aeither.store.assests.application.deleter;

import com.aeither.store.common.application.SetupDataDeleter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import com.aeither.store.assests.application.SubCategoryService;

@Component
@RequiredArgsConstructor
public class SubCategoryDataDeleter implements SetupDataDeleter {

    private final SubCategoryService subCategoryService;

    @Override
    public String getDeleteKey() {
        return "subcategory";
    }

    @Override
    public void delete(Long id) {
        subCategoryService.delete(id);
    }
}
