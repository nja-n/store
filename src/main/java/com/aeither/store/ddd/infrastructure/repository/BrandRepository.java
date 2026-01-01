package com.aeither.store.ddd.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aeither.store.ddd.domain.model.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
