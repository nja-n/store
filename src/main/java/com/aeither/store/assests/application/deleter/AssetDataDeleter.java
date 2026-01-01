package com.aeither.store.assests.application.deleter;

import com.aeither.store.common.application.SetupDataDeleter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import com.aeither.store.assests.application.AssetService;

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
