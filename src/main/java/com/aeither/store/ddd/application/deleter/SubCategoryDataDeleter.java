package com.aeither.store.ddd.application.deleter;

import com.aeither.store.ddd.application.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
