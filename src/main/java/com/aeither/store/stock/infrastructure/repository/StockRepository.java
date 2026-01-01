package com.aeither.store.stock.infrastructure.repository;

import com.aeither.store.stock.domain.model.Stock;
import com.aeither.store.stock.domain.repository.StockDomainRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aeither.store.administration.domain.model.Company;
import com.aeither.store.assests.domain.model.Asset;
import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long>, StockDomainRepository {
    List<Stock> findByCompany(Company company);

    Optional<Stock> findByCompanyAndAsset(Company company, Asset asset);
}
