package com.aeither.store.assests.domain.repository;

import com.aeither.store.assests.domain.model.Category;
import java.util.List;
import java.util.Optional;

public interface CategoryDomainRepository {
    List<Category> findAll();

    Optional<Category> findById(Long id);

    Category save(Category category);
}
