package com.aeither.store.assests.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.aeither.store.assests.domain.model.SubCategory;
import com.aeither.store.assests.domain.repository.SubCategoryDomainRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubCategoryService {

    private final SubCategoryDomainRepository subCategoryRepository;

    public List<SubCategory> findAll() {
        return subCategoryRepository.findAll().stream()
                .filter(sub -> !"DELETED".equals(sub.getStatus()))
                .toList();
    }

    public SubCategory save(SubCategory subCategory) {
        return subCategoryRepository.save(subCategory);
    }

    public SubCategory findById(Long id) {
        return subCategoryRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        SubCategory subCategory = findById(id);
        if (subCategory != null) {
            subCategory.setStatus("DELETED");
            subCategoryRepository.save(subCategory);
        }
    }
}
