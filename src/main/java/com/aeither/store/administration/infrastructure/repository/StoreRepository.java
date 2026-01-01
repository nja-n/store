package com.aeither.store.administration.infrastructure.repository;

import com.aeither.store.administration.domain.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> findByStatusNot(String status);
}
