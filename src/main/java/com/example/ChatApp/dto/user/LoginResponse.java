package com.example.ChatApp.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String message;
    private String token; // can be null if login failed
}
