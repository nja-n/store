package com.aeither.store.ddd.application;

import com.aeither.store.ddd.domain.model.Brand;
import com.aeither.store.ddd.infrastructure.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

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
