package com.aeither.store.administration.application.deleter;

import com.aeither.store.administration.application.UserService;
import com.aeither.store.common.application.SetupDataDeleter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDataDeleter implements SetupDataDeleter {

    private final UserService userService;

    @Override
    public String getDeleteKey() {
        return "user";
    }

    @Override
    public void delete(Long id) {
        userService.delete(id);
    }
}
