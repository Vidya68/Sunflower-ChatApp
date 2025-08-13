package com.example.ChatApp.service;

import com.example.ChatApp.dto.user.LoginResponse;
import com.example.ChatApp.dto.user.RegisterRequest;
import com.example.ChatApp.dto.user.MessageResponse;

public interface UserService {

    MessageResponse register(RegisterRequest request);

    LoginResponse login(String email, String password);
}
