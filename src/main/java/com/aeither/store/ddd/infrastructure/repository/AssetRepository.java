package com.aeither.store.ddd.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aeither.store.ddd.domain.model.Asset;

public interface AssetRepository extends JpaRepository<Asset, Long> {
}
