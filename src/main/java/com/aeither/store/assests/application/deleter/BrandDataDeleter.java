package com.aeither.store.assests.application.deleter;

import com.aeither.store.common.application.SetupDataDeleter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import com.aeither.store.assests.application.BrandService;

@Component
@RequiredArgsConstructor
public class BrandDataDeleter implements SetupDataDeleter {

    private final BrandService brandService;

    @Override
    public String getDeleteKey() {
        return "brand";
    }

    @Override
    public void delete(Long id) {
        brandService.delete(id);
    }
}
