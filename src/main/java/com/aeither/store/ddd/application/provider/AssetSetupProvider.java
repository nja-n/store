package com.aeither.store.ddd.application.provider;

import com.aeither.store.ddd.application.AssetService;
import com.aeither.store.ddd.domain.model.Asset;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AssetSetupProvider implements SetupDataProvider {

    private final AssetService assetService;

    @Override
    public String getKey() {
        // Since Asset is the final product, we might not list them all on the setup
        // page dropdowns,
        // but for consistency with the design pattern, we can return empty or all.
        // The original controller didn't send 'assets' list, so maybe we skip or just
        // add it.
        // Wait, the SetupController didn't send 'assets' list to the model.
        // But it DID send 'asset' new Object.
        return "assets";
    }

    @Override
    public Object getInitialState() {
        return new Asset();
    }

    @Override
    public String getInitialStateKey() {
        return "asset";
    }

    @Override
    public List<?> getList() {
        return assetService.findAll();
    }
}
