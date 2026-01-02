package com.aeither.store.assests.domain.repository;

import com.aeither.store.assests.domain.model.SubCategory;
import java.util.List;
import java.util.Optional;

public interface SubCategoryDomainRepository {
    List<SubCategory> findAll();

    Optional<SubCategory> findById(Long id);

    SubCategory save(SubCategory subCategory);
}
