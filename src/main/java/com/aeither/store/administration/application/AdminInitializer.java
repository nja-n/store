package com.aeither.store.administration.application;

import com.aeither.store.administration.domain.model.Role;
import com.aeither.store.administration.domain.model.User;
import com.aeither.store.administration.domain.repository.UserDomainRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminInitializer {

    private final UserDomainRepository userRepo;

    @PostConstruct
    public void init() {
        if (userRepo.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword("pass12");
            admin.setRole(Role.ADMIN);
            admin.setStatus("ACTIVE");
            userRepo.save(admin);
        }
    }
}
