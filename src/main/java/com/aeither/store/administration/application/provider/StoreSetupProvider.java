package com.aeither.store.administration.application.provider;

import com.aeither.store.administration.application.StoreService;
import com.aeither.store.administration.domain.model.Store;
import com.aeither.store.common.application.SetupDataProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StoreSetupProvider implements SetupDataProvider {

    private final StoreService storeService;
    private final com.aeither.store.administration.application.UserService userService;
    private final com.aeither.store.common.domain.AuthenticationContext authenticationContext;

    @Override
    public String getKey() {
        return "stores";
    }

    @Override
    public Object getInitialState() {
        return new Store();
    }

    @Override
    public String getInitialStateKey() {
        return "store";
    }

    @Override
    public List<?> getList() {
        com.aeither.store.administration.domain.model.User currentUser = userService
                .findByUsername(authenticationContext.getCurrentUsername());
        return storeService.findInContext(currentUser);
    }
}
