package com.example.ChatApp.service;

import com.example.ChatApp.dto.user.RegisterRequest;
import com.example.ChatApp.entity.User;



public interface UserService {
    User register(RegisterRequest request);
    String login(String email, String password);
}
