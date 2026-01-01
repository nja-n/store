package com.aeither.store.administration.infrastructure.repository;

import com.aeither.store.administration.domain.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    List<Company> findByStatusNot(String status);
}
