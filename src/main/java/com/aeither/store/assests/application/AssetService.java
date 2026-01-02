package com.aeither.store.assests.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.aeither.store.assests.domain.model.Asset;
import com.aeither.store.assests.domain.repository.AssetDomainRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssetService {

    private final AssetDomainRepository assetRepository;

    public List<Asset> findAll() {
        return assetRepository.findAll().stream()
                .filter(asset -> !"DELETED".equals(asset.getStatus()))
                .toList();
    }

    public Asset save(Asset asset) {
        StringBuilder fullName = new StringBuilder();
        if (asset.getSubCategory() != null) {
            if (asset.getSubCategory().getCategory() != null) {
                fullName.append(asset.getSubCategory().getCategory().getName()).append(" ");
            }
            fullName.append(asset.getSubCategory().getName()).append(" ");
        }
        if (asset.getBrand() != null) {
            fullName.append(asset.getBrand().getName()).append(" ");
        }
        if (asset.getModel() != null) {
            fullName.append(asset.getModel());
        }
        asset.setName(fullName.toString().trim());
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
