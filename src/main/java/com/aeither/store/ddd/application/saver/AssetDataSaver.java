package com.aeither.store.ddd.application.saver;

import com.aeither.store.ddd.application.AssetService;
import com.aeither.store.ddd.application.BrandService;
import com.aeither.store.ddd.application.SubCategoryService;
import com.aeither.store.ddd.domain.model.Asset;
import com.aeither.store.ddd.domain.model.Brand;
import com.aeither.store.ddd.domain.model.SubCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class AssetDataSaver implements SetupDataSaver {

    private final AssetService assetService;
    private final SubCategoryService subCategoryService;
    private final BrandService brandService;

    @Override
    public String getSaveKey() {
        return "asset";
    }

    @Override
    public void save(Map<String, String> requestData) {
        Asset asset;
        String idStr = requestData.get("id");
        if (idStr != null && !idStr.isEmpty()) {
            try {
                asset = assetService.findById(Long.parseLong(idStr));
            } catch (NumberFormatException e) {
                asset = null;
            }
            if (asset == null) {
                asset = new Asset();
            }
        } else {
            asset = new Asset();
        }

        asset.setSerialNumber(requestData.get("serialNumber"));
        asset.setModel(requestData.get("model"));
        asset.setAssetNumber(requestData.get("assetNumber"));
        if (asset.getId() == null) {
            asset.setStatus("ACTIVE");
        }
        // asset.setStatus(requestData.get("status")); // Removed status update from
        // frontend

        String subCategoryIdStr = requestData.get("subCategory.id");
        if (subCategoryIdStr != null && !subCategoryIdStr.isEmpty()) {
            SubCategory subCategory = subCategoryService.findById(Long.parseLong(subCategoryIdStr));
            asset.setSubCategory(subCategory);
        }

        String brandIdStr = requestData.get("brand.id");
        if (brandIdStr != null && !brandIdStr.isEmpty()) {
            Brand brand = brandService.findById(Long.parseLong(brandIdStr));
            asset.setBrand(brand);
        }

        assetService.save(asset);
    }
}
