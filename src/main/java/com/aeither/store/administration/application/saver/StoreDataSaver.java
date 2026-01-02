package com.aeither.store.administration.application.saver;

import com.aeither.store.administration.application.StoreService;
import com.aeither.store.administration.application.UserService;
import com.aeither.store.administration.domain.model.Store;
import com.aeither.store.administration.domain.model.User;
import com.aeither.store.common.application.SetupDataSaver;
import com.aeither.store.common.domain.AuthenticationContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class StoreDataSaver implements SetupDataSaver {

    private final StoreService storeService;
    private final UserService userService;
    private final AuthenticationContext authenticationContext;

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
        store.setGstNumber(requestData.get("gstNumber"));
        store.setAddress(requestData.get("address"));
        store.setContactPerson(requestData.get("contactPerson"));
        store.setMobileNumber(requestData.get("mobileNumber"));
        store.setEmail(requestData.get("email"));

        // Automated login flag
        boolean loginEnabled = Boolean.parseBoolean(requestData.get("loginEnabled"));
        store.setLoginEnabled(loginEnabled);

        // Association with current company admin's company
        String currentUsername = authenticationContext.getCurrentUsername();
        User currentUser = userService.findByUsername(currentUsername);

        if (currentUser != null && currentUser.getCompany() != null) {
            store.setCompanyId(currentUser.getCompany().getId());
        }

        store = storeService.save(store);

        // Automated User management for store login delegated to UserService
        userService.ensureStoreUserExists(store, loginEnabled, currentUser);
    }
}
