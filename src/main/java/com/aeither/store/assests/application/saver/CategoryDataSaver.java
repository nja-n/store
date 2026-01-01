package com.aeither.store.assests.application.saver;

import com.aeither.store.common.application.SetupDataSaver;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import com.aeither.store.assests.application.CategoryService;
import com.aeither.store.assests.domain.model.Category;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class CategoryDataSaver implements SetupDataSaver {

    private final CategoryService categoryService;

    @Override
    public String getSaveKey() {
        return "category";
    }

    @Override
    public void save(Map<String, String> requestData) {
        Category category;
        String idStr = requestData.get("id");
        if (idStr != null && !idStr.isEmpty()) {
            category = categoryService.findById(Long.parseLong(idStr));
            if (category == null) {
                category = new Category();
            }
        } else {
            category = new Category();
        }
        category.setName(requestData.get("name"));
        categoryService.save(category);
    }
}
