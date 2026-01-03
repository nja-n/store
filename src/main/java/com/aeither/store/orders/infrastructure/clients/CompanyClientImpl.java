package com.aeither.store.orders.infrastructure.clients;

import com.aeither.store.administration.application.CompanyService;
import com.aeither.store.administration.domain.model.Company;
import com.aeither.store.orders.application.CompanyClient;

import org.springframework.stereotype.Component;

@Component
public class CompanyClientImpl implements CompanyClient {
    
    private final CompanyService service;

    public CompanyClientImpl(CompanyService service) {
        this.service = service;
    }
    
    @Override
    public Company getCompany(Long companyId) {
        return service.findById(companyId);
    }
}
