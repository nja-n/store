package com.aeither.store.administration.domain.repository;

import com.aeither.store.administration.domain.model.Store;
import java.util.List;
import java.util.Optional;

public interface StoreDomainRepository {
    List<Store> findByStatusNot(String status);

    Optional<Store> findById(Long id);

    Store save(Store store);

    List<Store> findByCompanyIdAndStatusNot(Long companyId, String status);

    List<Store> findByCompanyIdAndNameContainingIgnoreCase(Long companyId, String name);
}
