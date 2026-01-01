package com.aeither.store.ddd.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aeither.store.ddd.domain.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
