package com.aeither.store.ddd.application.provider;

import com.aeither.store.ddd.application.BrandService;
import com.aeither.store.ddd.domain.model.Brand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BrandSetupProvider implements SetupDataProvider {

    private final BrandService brandService;

    @Override
    public String getKey() {
        return "brands";
    }

    @Override
    public Object getInitialState() {
        return new Brand();
    }

    @Override
    public String getInitialStateKey() {
        return "brand";
    }

    @Override
    public List<?> getList() {
        return brandService.findAll();
    }
}
