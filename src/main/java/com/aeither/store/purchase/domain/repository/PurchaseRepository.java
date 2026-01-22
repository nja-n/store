package com.aeither.store.purchase.domain.repository;

import com.aeither.store.administration.domain.model.Company;
import com.aeither.store.purchase.domain.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    @Query("""
        select distinct p
        from Purchase p
        left join fetch p.items
        where p.company = :company
        order by p.createdTime desc
    """)
    List<Purchase> findByCompanyOrderByCreatedTimeDesc(Company company);
}
