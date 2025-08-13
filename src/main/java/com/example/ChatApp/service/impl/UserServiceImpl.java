package com.example.ChatApp.service.impl;

import com.example.ChatApp.dto.user.RegisterRequest;
import com.example.ChatApp.entity.User;
import com.example.ChatApp.repositories.UserRepository;
import com.example.ChatApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public User register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        User user = User.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .displayName(request.getDisplayName())
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .build();
        return userRepository.save(user);
    }

    @Override
    public String login(String email, String password) {
        // Will be replaced with JWT later
        return "JWT_TOKEN_PLACEHOLDER";
    }
}

//@Service
//@RequiredArgsConstructor
//public class UserServiceImpl implements UserService {
//
//    private final UserRepository userRepository;
//    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//    @Override
//    public User register(RegisterRequest request) {
//        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
//            throw new RuntimeException("Email already exists");
//        }
//        User user = User.builder()
//                .email(request.getEmail())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .displayName(request.getDisplayName())
//                .build();
//        return userRepository.save(user);
//    }
//
//    @Override
//    public String login(String email, String password) {
//        // TODO: Implement JWT token generation
//        return "JWT_TOKEN_PLACEHOLDER";
//    }
//}
