package com.example.ChatApp.controller;

import com.example.ChatApp.dto.user.UpdateProfileRequest;
import com.example.ChatApp.dto.user.UserResponse;
import com.example.ChatApp.security.CustomUserDetails;
import com.example.ChatApp.service.UserService;
import jakarta.validation.Valid;
import com.example.ChatApp.dto.user.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/profile")
    public ResponseEntity<UserResponse> updateProfile(
            @Valid @RequestBody UpdateProfileRequest request,
            Authentication authentication) {

        // Extract userId from JWT token
        Long userId = ((CustomUserDetails) authentication.getPrincipal()).getId();

        UserResponse updatedUser = userService.updateProfile(userId, request);

        return ResponseEntity.ok(updatedUser);
    }
}
