package com.aeither.store.administration.application.saver;

import com.aeither.store.administration.application.CompanyService;
import com.aeither.store.administration.domain.model.Company;
import com.aeither.store.common.application.SetupDataSaver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class CompanyDataSaver implements SetupDataSaver {

    private final CompanyService companyService;

    @Override
    public String getSaveKey() {
        return "company";
    }

    @Override
    public void save(Map<String, String> requestData) {
        Company company;
        String idStr = requestData.get("id");
        if (idStr != null && !idStr.isEmpty()) {
            company = companyService.findById(Long.parseLong(idStr));
            if (company == null) {
                company = new Company();
            }
        } else {
            company = new Company();
        }

        company.setName(requestData.get("name"));
        company.setGstin(requestData.get("gstin"));
        company.setAddress(requestData.get("address"));
        company.setPhone(requestData.get("phone"));
        company.setEmail(requestData.get("email"));
        company.setWebsite(requestData.get("website"));
        company.setLogo(requestData.get("logo"));
        company.setStatus(requestData.get("status"));

        companyService.save(company);
    }
}
