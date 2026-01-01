package com.aeither.store.administration.application.deleter;

import com.aeither.store.administration.application.StoreService;
import com.aeither.store.common.application.SetupDataDeleter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StoreDataDeleter implements SetupDataDeleter {

    private final StoreService storeService;

    @Override
    public String getDeleteKey() {
        return "store";
    }

    @Override
    public void delete(Long id) {
        storeService.delete(id);
    }
}
