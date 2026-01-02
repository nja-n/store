package com.aeither.store.assests.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.aeither.store.assests.domain.model.Brand;
import com.aeither.store.assests.domain.repository.BrandDomainRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandDomainRepository brandRepository;

    public List<Brand> findAll() {
        return brandRepository.findAll().stream()
                .filter(brand -> !"DELETED".equals(brand.getStatus()))
                .toList();
    }

    public Brand save(Brand brand) {
        return brandRepository.save(brand);
    }

    public Brand findById(Long id) {
        return brandRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        Brand brand = findById(id);
        if (brand != null) {
            brand.setStatus("DELETED");
            brandRepository.save(brand);
        }
    }
}
