package com.aeither.store.orders.application;

import com.aeither.store.administration.domain.model.Company;

public interface CompanyClient {
    Company getCompany(Long companyId);
}
