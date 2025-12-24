package com.aeither.store.entities.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aeither.store.entities.Asset;

import jakarta.transaction.Transactional;

@Repository
public interface AssetsRepo extends JpaRepository<Asset, Long> {

    List<Asset> findAllByStatus(String string);

    @Query("update Asset as s set s.status = ?2 where s.id = ?1")
    @Transactional
    @Modifying
    void updateStatusById(long id, String string);
    
}
