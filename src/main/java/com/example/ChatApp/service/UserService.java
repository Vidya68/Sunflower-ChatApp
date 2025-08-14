package com.example.ChatApp.service;

import com.example.ChatApp.dto.user.*;

public interface UserService {

    MessageResponse register(RegisterRequest request);

    LoginResponse login(String email, String password);
    UserResponse updateProfile(Long userId, UpdateProfileRequest request);
}
