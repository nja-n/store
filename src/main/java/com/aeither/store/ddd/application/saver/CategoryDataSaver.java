package com.aeither.store.ddd.application.saver;

import com.aeither.store.ddd.application.CategoryService;
import com.aeither.store.ddd.domain.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
