package com.aeither.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aeither.store.entities.Asset;
import com.aeither.store.services.AssetService;

@RestController
@RequestMapping("/assets")
public class StoreController {

    @Autowired
    private AssetService assetService; 

    @PostMapping("")
    public ResponseEntity<Asset> acceptAssets(@RequestBody Asset asset
    , Long user){
        asset.setId(null);
        assetService.acceptAssets(asset, 1L);
        return ResponseEntity.ok().body(asset);
    }

    @GetMapping("")
    public ResponseEntity<List<Asset>> all(){
        return ResponseEntity.ok().body(
            assetService.getAllAssets()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asset> getOne(@PathVariable String id){
        return ResponseEntity.ok(assetService.getById(Long.parseLong(id)));
    }

    @PostMapping("/update")
    public ResponseEntity<Asset> update(@RequestBody Asset asset){
        return ResponseEntity.ok(assetService.updateAsset(asset));
    }

    @PostMapping("/delete/{id}")
    public String deleteById(@PathVariable String id){
        return assetService.deleteById(Long.parseLong(id));
    }
    
}
