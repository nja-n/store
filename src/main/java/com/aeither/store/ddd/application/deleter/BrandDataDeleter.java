package com.aeither.store.ddd.application.deleter;

import com.aeither.store.ddd.application.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
