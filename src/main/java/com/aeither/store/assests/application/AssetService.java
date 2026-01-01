package com.aeither.store.assests.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.aeither.store.assests.domain.model.Asset;
import com.aeither.store.assests.infrastructure.repository.AssetRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssetService {

    private final AssetRepository assetRepository;

    public List<Asset> findAll() {
        return assetRepository.findAll().stream()
                .filter(asset -> !"DELETED".equals(asset.getStatus()))
                .toList();
    }

    public Asset save(Asset asset) {
        return assetRepository.save(asset);
    }

    public Asset findById(Long id) {
        return assetRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        Asset asset = assetRepository.findById(id).orElse(null);
        if (asset != null) {
            asset.setStatus("DELETED");
            assetRepository.save(asset);
        }
    }
}
