package com.aeither.store.assests.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aeither.store.assests.domain.model.SubCategory;
import com.aeither.store.assests.domain.repository.SubCategoryDomainRepository;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long>, SubCategoryDomainRepository {
}
