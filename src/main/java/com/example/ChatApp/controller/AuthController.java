package com.example.ChatApp.controller;

import com.example.ChatApp.dto.user.LoginRequest;
import com.example.ChatApp.dto.user.RegisterRequest;
import com.example.ChatApp.entity.User;
import com.example.ChatApp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public User register(@Valid @RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginRequest request) {
        return userService.login(request.getEmail(), request.getPassword());
    }
}
