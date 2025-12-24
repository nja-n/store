package com.aeither.store.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aeither.store.entities.Asset;
import com.aeither.store.entities.User;
import com.aeither.store.entities.repo.AssetsRepo;

@Service
public class AssetService {

    @Autowired
    AssetsRepo assetsRepo;

    public Asset acceptAssets(Asset asset, Long userId) {
        asset.setStatus("Y");
        asset.setAddedUser(new User());
        asset.getAddedUser().setId(userId);
        asset.setAddedDate(new Date());

        assetsRepo.save(asset);
        return asset;
    }

    public List<Asset> getAllAssets() {
        // return assetsRepo.findAll();
        return assetsRepo.findAllByStatus("Y");
    }

    public Asset getById(long id) {
        return assetsRepo.findById(id).orElse(null);
    }

    public Asset updateAsset(Asset asset) {
        return assetsRepo.save(asset);
    }

    public String deleteById(long id) {
        assetsRepo.updateStatusById(id, "N");
        return "Y";
    }
    
}
