package com.aeither.store.administration.application;

import com.aeither.store.administration.domain.model.Role;
import com.aeither.store.administration.domain.model.User;
import com.aeither.store.administration.domain.repository.UserDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDomainRepository userRepository;

    public List<User> findAll() {
        return userRepository.findByStatusNot("DELETED");
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public User save(User user) {
        if (user.getStatus() == null) {
            user.setStatus("ACTIVE");
        }
        if (user.getRole() == null) {
            if (user.getUsername().equals("admin")) {
                user.setRole(Role.ADMIN);
            } else if (user.getCompany() != null) {
                user.setRole(Role.COMPANY_ADMIN);
            } else {
                user.setRole(Role.STORE_USER);
            }
        }
        return userRepository.save(user);
    }

    public User findByStoreId(Long storeId) {
        return userRepository.findByStoreIdAndStatus(storeId, "ACTIVE").orElse(null);
    }

    public void delete(Long id) {
        User user = findById(id);
        if (user != null) {
            user.setStatus("DELETED");
            userRepository.save(user);
        }
    }

    public void ensureStoreUserExists(com.aeither.store.administration.domain.model.Store store, boolean enabled,
            User creator) {
        User storeUser = findByStoreId(store.getId());

        if (enabled) {
            if (storeUser == null) {
                storeUser = new User();
                storeUser.setUsername("store_" + store.getName().toLowerCase().replace(" ", "_"));
                storeUser.setPassword("password123");
                storeUser.setRole(Role.STORE_USER);
                storeUser.setStore(store);
                if (creator != null) {
                    storeUser.setCompany(creator.getCompany());
                }
                save(storeUser);
            } else if ("DELETED".equals(storeUser.getStatus())) {
                storeUser.setStatus("ACTIVE");
                save(storeUser);
            }
        } else {
            if (storeUser != null && !"DELETED".equals(storeUser.getStatus())) {
                delete(storeUser.getId());
            }
        }
    }
}
