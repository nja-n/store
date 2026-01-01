// package com.aeither.store.services;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.aeither.store.entities.User;
// import com.aeither.store.entities.repo.UserRepo;
// import com.aeither.store.settings.JwtUtil;

// @Service
// public class AuthService {

//     @Autowired
//     UserRepo userRepo;
//     @Autowired
//     JwtUtil jwtUtil;

//     public String login(String username, String password) {
//         if ("admin".equals(username) && "password".equals(password)) {
//             return jwtUtil.generateToken(username);
//         } else {
//             User user = userRepo.findByUsernameAndPassword(username, password);
//             if (user != null) {
//                 return jwtUtil.generateToken(username);
//             } else {
//                 user = new User();
//                 user.setUsername(username);
//                 user.setPassword(password);
//                 user = userRepo.save(user);
//                 return jwtUtil.generateToken(username);
//             }
//         }

//     }

//     public List<String> userinfo() {
//         List<User> users = userRepo.findAll();
//         List<String> usernames = users.stream()
//                 .map(User::getUsername)
//                 .toList();

//         return usernames;
//     }

//     public void logout(Long userId) {
//         // No action needed for JWT logout    
//     }
    

// }
