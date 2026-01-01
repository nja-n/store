package com.aeither.store.administration.application.saver;

import com.aeither.store.administration.application.CompanyService;
import com.aeither.store.administration.application.StoreService;
import com.aeither.store.administration.application.UserService;
import com.aeither.store.administration.domain.model.Company;
import com.aeither.store.administration.domain.model.Role;
import com.aeither.store.administration.domain.model.Store;
import com.aeither.store.administration.domain.model.User;
import com.aeither.store.common.application.SetupDataSaver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class UserDataSaver implements SetupDataSaver {

    private final UserService userService;
    private final CompanyService companyService;
    private final StoreService storeService;

    @Override
    public String getSaveKey() {
        return "user";
    }

    @Override
    public void save(Map<String, String> requestData) {
        User user;
        String idStr = requestData.get("id");
        if (idStr != null && !idStr.isEmpty()) {
            user = userService.findById(Long.parseLong(idStr));
            if (user == null) {
                user = new User();
            }
        } else {
            user = new User();
        }

        user.setUsername(requestData.get("username"));
        if (requestData.containsKey("password") && !requestData.get("password").isEmpty()) {
            // In real world, ENCRYPT PASSWORD HERE
            user.setPassword(requestData.get("password"));
        } else if (user.getId() == null) {
            // Default password for new users if not provided? Or error?
            user.setPassword("password");
        }

        String roleStr = requestData.get("role");
        if (roleStr != null && !roleStr.isEmpty()) {
            user.setRole(Role.valueOf(roleStr));
        }

        String companyIdStr = requestData.get("companyId");
        if (companyIdStr != null && !companyIdStr.isEmpty()) {
            Company company = companyService.findById(Long.parseLong(companyIdStr));
            user.setCompany(company);
        }

        String storeIdStr = requestData.get("storeId");
        if (storeIdStr != null && !storeIdStr.isEmpty()) {
            Store store = storeService.findById(Long.parseLong(storeIdStr));
            user.setStore(store);
        }

        userService.save(user);
    }
}
