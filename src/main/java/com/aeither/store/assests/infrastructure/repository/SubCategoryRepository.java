package com.aeither.store.assests.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aeither.store.assests.domain.model.SubCategory;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
}
