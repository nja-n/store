package com.aeither.store.administration.application;

import com.aeither.store.administration.domain.model.Store;
import com.aeither.store.administration.infrastructure.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    public List<Store> findAll() {
        return storeRepository.findByStatusNot("DELETED");
    }

    public Store findById(Long id) {
        return storeRepository.findById(id).orElse(null);
    }

    public Store save(Store store) {
        if (store.getStatus() == null) {
            store.setStatus("ACTIVE");
        }
        return storeRepository.save(store);
    }

    public void delete(Long id) {
        Store store = findById(id);
        if (store != null) {
            store.setStatus("DELETED");
            storeRepository.save(store);
        }
    }
}
