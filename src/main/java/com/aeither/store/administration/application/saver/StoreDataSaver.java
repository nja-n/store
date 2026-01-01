package com.aeither.store.administration.application.saver;

import com.aeither.store.administration.application.StoreService;
import com.aeither.store.administration.domain.model.Store;
import com.aeither.store.common.application.SetupDataSaver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class StoreDataSaver implements SetupDataSaver {

    private final StoreService storeService;

    @Override
    public String getSaveKey() {
        return "store";
    }

    @Override
    public void save(Map<String, String> requestData) {
        Store store;
        String idStr = requestData.get("id");
        if (idStr != null && !idStr.isEmpty()) {
            store = storeService.findById(Long.parseLong(idStr));
            if (store == null) {
                store = new Store();
            }
        } else {
            store = new Store();
        }

        store.setName(requestData.get("name"));
        store.setLocation(requestData.get("location"));

        // Independent Store, no Company link

        storeService.save(store);
    }
}
