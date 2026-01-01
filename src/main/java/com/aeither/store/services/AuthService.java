package com.aeither.store.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aeither.store.administration.domain.model.User;
import com.aeither.store.administration.domain.model.Role;
import com.aeither.store.administration.domain.repository.UserDomainRepository;
import com.aeither.store.settings.JwtUtil;

@Service
public class AuthService {

    @Autowired
    private UserDomainRepository userRepo;
    @Autowired
    private JwtUtil jwtUtil;

    public String login(String username, String password) {
        Optional<User> userOpt = userRepo.findByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getPassword().equals(password)) {
                return jwtUtil.generateToken(username);
            } else {
                return "Invalid Credentials";
            }
        } else {
            // Auto-signup logic for convenience (can be removed for strict setup)
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setRole(Role.STORE_USER);
            user.setStatus("ACTIVE");
            user = userRepo.save(user);
            return jwtUtil.generateToken(username);
        }
    }

    public List<String> userinfo() {
        List<User> users = userRepo.findAll();
        List<String> usernames = users.stream()
                .map(User::getUsername)
                .toList();

        return usernames;
    }

    public void logout(Long userId) {
        // No action needed for JWT logout
    }
}
