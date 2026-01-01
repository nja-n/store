package com.aeither.store.administration.application.deleter;

import com.aeither.store.administration.application.CompanyService;
import com.aeither.store.common.application.SetupDataDeleter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompanyDataDeleter implements SetupDataDeleter {

    private final CompanyService companyService;

    @Override
    public String getDeleteKey() {
        return "company";
    }

    @Override
    public void delete(Long id) {
        companyService.delete(id);
    }
}
