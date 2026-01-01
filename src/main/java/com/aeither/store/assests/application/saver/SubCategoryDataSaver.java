package com.aeither.store.assests.application.saver;

import com.aeither.store.common.application.SetupDataSaver;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import com.aeither.store.assests.application.CategoryService;
import com.aeither.store.assests.application.SubCategoryService;
import com.aeither.store.assests.domain.model.Category;
import com.aeither.store.assests.domain.model.SubCategory;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class SubCategoryDataSaver implements SetupDataSaver {

    private final SubCategoryService subCategoryService;
    private final CategoryService categoryService;

    @Override
    public String getSaveKey() {
        return "subcategory";
    }

    @Override
    public void save(Map<String, String> requestData) {
        SubCategory subCategory;
        String idStr = requestData.get("id");
        if (idStr != null && !idStr.isEmpty()) {
            subCategory = subCategoryService.findById(Long.parseLong(idStr));
            if (subCategory == null) {
                subCategory = new SubCategory();
            }
        } else {
            subCategory = new SubCategory();
        }
        subCategory.setName(requestData.get("name"));

        String categoryIdStr = requestData.get("category.id");
        if (categoryIdStr != null && !categoryIdStr.isEmpty()) {
            Category category = categoryService.findById(Long.parseLong(categoryIdStr));
            subCategory.setCategory(category);
        }

        subCategoryService.save(subCategory);
    }
}
