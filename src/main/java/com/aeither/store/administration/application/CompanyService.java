package com.aeither.store.administration.application;

import com.aeither.store.administration.domain.model.Company;
import com.aeither.store.administration.infrastructure.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public List<Company> findAll() {
        return companyRepository.findByStatusNot("DELETED");
    }

    public Company findById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    public Company save(Company company) {
        if (company.getStatus() == null) {
            company.setStatus("ACTIVE");
        }
        return companyRepository.save(company);
    }

    public void delete(Long id) {
        Company company = findById(id);
        if (company != null) {
            company.setStatus("DELETED");
            companyRepository.save(company);
        }
    }
}
