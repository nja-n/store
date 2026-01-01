package com.aeither.store.administration.application.provider;

import com.aeither.store.administration.application.CompanyService;
import com.aeither.store.administration.domain.model.Company;
import com.aeither.store.common.application.SetupDataProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CompanySetupProvider implements SetupDataProvider {

    private final CompanyService companyService;

    @Override
    public String getKey() {
        return "companies";
    }

    @Override
    public Object getInitialState() {
        return new Company();
    }

    @Override
    public String getInitialStateKey() {
        return "company";
    }

    @Override
    public List<?> getList() {
        return companyService.findAll();
    }
}
