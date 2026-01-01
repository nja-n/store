package com.aeither.store.assests.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aeither.store.assests.domain.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
