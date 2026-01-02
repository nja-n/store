package com.aeither.store.assests.domain.repository;

import com.aeither.store.assests.domain.model.Brand;
import java.util.List;
import java.util.Optional;

public interface BrandDomainRepository {
    List<Brand> findAll();

    Optional<Brand> findById(Long id);

    Brand save(Brand brand);
}
