package com.example.ChatApp.service.impl;

import com.example.ChatApp.dto.user.LoginResponse;
import com.example.ChatApp.dto.user.MessageResponse;
import com.example.ChatApp.dto.user.RegisterRequest;
import com.example.ChatApp.dto.user.UserResponse;
import com.example.ChatApp.entity.User;
import com.example.ChatApp.dto.user.*;
import com.example.ChatApp.repositories.UserRepository;
import com.example.ChatApp.security.CustomUserDetails;
import com.example.ChatApp.security.JwtUtil;
import com.example.ChatApp.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

//
//@Service
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           AuthenticationManager authenticationManager,
                           UserDetailsService userDetailsService,
                           JwtUtil jwtUtil,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public MessageResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return new MessageResponse("Email already exists");
        }
        if (userRepository.existsByUsername(request.getUsername())) {
            return new MessageResponse("Username already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setDisplayName(request.getDisplayName() != null && !request.getDisplayName().isBlank()
                ? request.getDisplayName()
                : request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);
        return new MessageResponse("User registered successfully");
    }
    @Override
    public LoginResponse login(String email, String password) {

        // check if user exists first
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            return new LoginResponse("User does not exist", null);
        }

        try {
            // try authentication
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );

            // if auth succeeds, generate JWT
            CustomUserDetails userDetails =
                    (CustomUserDetails) userDetailsService.loadUserByUsername(email);
            String token = jwtUtil.generateToken(userDetails.getUsername());

            return new LoginResponse("Logged in successfully", token);

        } catch (Exception e) {
            // bad credentials
            return new LoginResponse("Invalid credentials", null);
        }

    }

    @Override
    public UserResponse updateProfile(Long userId, UpdateProfileRequest request) {
        // Step 1: Fetch user from DB
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Step 2: Update fields
        user.setDisplayName(request.getDisplayName());
        user.setBio(request.getBio());

        // Step 3: Save user
        User updatedUser = userRepository.save(user);

        // Step 4: Convert to UserResponse DTO
        return UserResponse.builder()
                .id(updatedUser.getId())
                .username(updatedUser.getUsername())
                .displayName(updatedUser.getDisplayName())
                .email(updatedUser.getEmail())
                .bio(updatedUser.getBio())
                .profilePicture(updatedUser.getProfilePicture())
                .createdAt(updatedUser.getCreatedAt())
                .build();
    }

}

//public class UserServiceImpl implements UserService {
//
//    private final UserRepository userRepository;
//    private final AuthenticationManager authenticationManager;
//    private final UserDetailsService userDetailsService;
//    private final JwtUtil jwtUtil;
//    private final PasswordEncoder passwordEncoder;
//    public UserServiceImpl(UserRepository userRepository,
//                           AuthenticationManager authenticationManager,
//                           UserDetailsService userDetailsService,
//                           JwtUtil jwtUtil,
//                           PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.authenticationManager = authenticationManager;
//        this.userDetailsService = userDetailsService;
//        this.jwtUtil = jwtUtil;
//        this.passwordEncoder = passwordEncoder;
//    }
////    public UserServiceImpl(UserRepository userRepository,
////                           AuthenticationManager authenticationManager,
////                           UserDetailsService userDetailsService,
////                           JwtUtil jwtUtil) {
////        this.userRepository = userRepository;
////        this.authenticationManager = authenticationManager;
////        this.userDetailsService = userDetailsService;
////        this.jwtUtil = jwtUtil;
////    }
//
//    @Override
//    public User register(RegisterRequest request) {
//        User user = new User();
//        user.setUsername(request.getUsername());      // from request
//        user.setDisplayName(request.getDisplayName()); // from request
//        user.setEmail(request.getEmail());
//
//        // hash the password before saving
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
//
//        return userRepository.save(user);
//    }
////    public User register(RegisterRequest request) {
////        User user = new User();
////        user.setUsername(request.getUsername());
////        user.setDisplayName(request.getDisplayName());
////        user.setPassword(passwordEncoder.encode(request.getPassword()));
////        return userRepository.save(user);
////    }
//
//    @Override
//    public String login(String email, String password) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(email, password)
//        );
//
//        CustomUserDetails userDetails =
//                (CustomUserDetails) userDetailsService.loadUserByUsername(email);
//
//        return jwtUtil.generateToken(userDetails.getUsername());
//    }
//}
