package com.aeither.store.administration.application.provider;

import com.aeither.store.administration.application.UserService;
import com.aeither.store.administration.domain.model.User;
import com.aeither.store.common.application.SetupDataProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserSetupProvider implements SetupDataProvider {

    private final UserService userService;

    @Override
    public String getKey() {
        return "users";
    }

    @Override
    public Object getInitialState() {
        return new User();
    }

    @Override
    public String getInitialStateKey() {
        return "user";
    }

    @Override
    public List<?> getList() {
        return userService.findAll();
    }
}
