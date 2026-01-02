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
}
