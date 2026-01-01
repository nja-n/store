package com.aeither.store.stock.domain.repository;

import com.aeither.store.administration.domain.model.Company;
import com.aeither.store.assests.domain.model.Asset;
import com.aeither.store.stock.domain.model.Stock;
import java.util.List;
import java.util.Optional;

public interface StockDomainRepository {
    List<Stock> findByCompany(Company company);

    Optional<Stock> findByCompanyAndAsset(Company company, Asset asset);

    Stock save(Stock stock);

    Optional<Stock> findById(Long id);

    void delete(Stock stock);
}
