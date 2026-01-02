package com.aeither.store.administration.infrastructure.repository;

import com.aeither.store.administration.domain.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.aeither.store.administration.domain.repository.StoreDomainRepository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long>, StoreDomainRepository {
    List<Store> findByStatusNot(String status);

    List<Store> findByCompanyIdAndStatusNot(Long companyId, String status);
}
