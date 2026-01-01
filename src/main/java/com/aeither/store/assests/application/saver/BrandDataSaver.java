package com.aeither.store.assests.application.saver;

import com.aeither.store.common.application.SetupDataSaver;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import com.aeither.store.assests.application.BrandService;
import com.aeither.store.assests.domain.model.Brand;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class BrandDataSaver implements SetupDataSaver {

    private final BrandService brandService;

    @Override
    public String getSaveKey() {
        return "brand";
    }

    @Override
    public void save(Map<String, String> requestData) {
        Brand brand;
        String idStr = requestData.get("id");
        if (idStr != null && !idStr.isEmpty()) {
            brand = brandService.findById(Long.parseLong(idStr));
            if (brand == null) {
                brand = new Brand();
            }
        } else {
            brand = new Brand();
        }
        brand.setName(requestData.get("name"));
        brandService.save(brand);
    }
}
