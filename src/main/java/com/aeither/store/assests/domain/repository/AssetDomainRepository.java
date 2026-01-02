package com.aeither.store.assests.domain.repository;

import com.aeither.store.assests.domain.model.Asset;
import java.util.List;
import java.util.Optional;

public interface AssetDomainRepository {
    List<Asset> findAll();

    Optional<Asset> findById(Long id);

    Asset save(Asset asset);

    void deleteById(Long id);
}
