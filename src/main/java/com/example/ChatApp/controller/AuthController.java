package com.example.ChatApp.controller;

import com.example.ChatApp.dto.user.*;
import com.example.ChatApp.entity.User;
import com.example.ChatApp.payload.JwtResponse;
import com.example.ChatApp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    @PostMapping("/register")
    public MessageResponse register(@RequestBody RegisterRequest request) {
        return userService.register(request);
    }

//
//    @PostMapping("/register")
////    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request) {
////        MessageResponse response = userService.register(request);
////        return ResponseEntity.ok(response);
////    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(userService.login(request.getEmail(), request.getPassword()));
    }

}

//@RestController
//@RequestMapping("/api/auth")
//@RequiredArgsConstructor
//public class AuthController {
//
//    private final UserService userService;
//
//    @PostMapping("/register")
//    public User register(@Valid @RequestBody RegisterRequest request) {
//        return userService.register(request);
//    }
//
//    @PostMapping("/login")
//    public String login(@Valid @RequestBody LoginRequest request) {
//        return userService.login(request.getEmail(), request.getPassword());
//    }
//}
