package com.aeither.store.administration.web;

import com.aeither.store.administration.application.UserService;
import com.aeither.store.administration.domain.model.User;
import com.aeither.store.common.domain.AuthenticationContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;
    private final AuthenticationContext authenticationContext;

    @GetMapping
    public String showProfile(Model model) {
        String username = authenticationContext.getCurrentUsername();
        if (username != null) {
            User user = userService.findAll().stream()
                    .filter(u -> u.getUsername().equals(username))
                    .findFirst()
                    .orElse(null);
            model.addAttribute("user", user);
        }
        return "profile";
    }

    @PostMapping("/update")
    public String updateProfile(@RequestParam String username, @RequestParam(required = false) String password) {
        String currentUsername = authenticationContext.getCurrentUsername();
        User user = userService.findAll().stream()
                .filter(u -> u.getUsername().equals(currentUsername))
                .findFirst()
                .orElse(null);

        if (user != null) {
            user.setUsername(username);
            if (password != null && !password.isEmpty()) {
                user.setPassword(password); // In real world, encrypt!
            }
            userService.save(user);
        }
        return "redirect:/profile?success";
    }
}
