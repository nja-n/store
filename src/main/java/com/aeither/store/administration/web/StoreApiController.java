package com.aeither.store.administration.web;

import com.aeither.store.administration.application.StoreService;
import com.aeither.store.administration.application.UserService;
import com.aeither.store.administration.domain.model.Store;
import com.aeither.store.administration.domain.model.User;
import com.aeither.store.common.domain.AuthenticationContext;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreApiController {

    private final StoreService storeService;
    private final UserService userService;
    private final AuthenticationContext authenticationContext;

    @GetMapping("/search")
    public List<Store> search(@RequestParam String query) {
        User currentUser = userService.findByUsername(authenticationContext.getCurrentUsername());

        if (currentUser == null || currentUser.getCompany() == null) {
            return List.of();
        }

        return storeService.search(currentUser.getCompany().getId(), query);
    }
}
