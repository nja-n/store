package com.aeither.store.administration.domain.repository;

import com.aeither.store.administration.domain.model.Company;
import java.util.List;
import java.util.Optional;

public interface CompanyDomainRepository {
    List<Company> findByStatusNot(String status);

    Optional<Company> findById(Long id);

    Company save(Company company);
}
