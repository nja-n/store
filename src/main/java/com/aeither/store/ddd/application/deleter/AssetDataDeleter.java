package com.aeither.store.ddd.application.deleter;

import com.aeither.store.ddd.application.AssetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AssetDataDeleter implements SetupDataDeleter {

    private final AssetService assetService;

    @Override
    public String getDeleteKey() {
        return "asset";
    }

    @Override
    public void delete(Long id) {
        assetService.delete(id);
    }
}
