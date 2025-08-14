package com.example.ChatApp.controller;

import com.example.ChatApp.dto.user.UpdateProfileRequest;
import com.example.ChatApp.dto.user.UserResponse;
import com.example.ChatApp.entity.User;
import com.example.ChatApp.repositories.UserRepository;
import com.example.ChatApp.security.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.http.MediaType;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

    @SpringBootTest
    @AutoConfigureMockMvc
    class UserControllerIntegrationTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;
        @Autowired
        private JwtUtil jwtUtil;
        @Autowired
        private UserRepository userRepository;
        @Value("${JWT_SECRET}")
        private String jwtSecret;
        private String jwt;

        @BeforeEach
        void setUp() {
            String secret = "my_super_secret_key_that_is_long_enough_123456";

            User user = new User();
            user.setEmail("vidyaaaaaaaaaaaaa.com");
            user.setUsername("vidy"); // ✅ Fix
            user.setPassword("vidya"); // In real life, hash it
            user.setDisplayName("vidyaaa");
            user.setBio("vidya's bioooooooooo, new one,");
            user.setIsActive(true);
            userRepository.save(user);

            jwt = jwtUtil.generateToken("vidyaa@example.com");
        }


        @Test
        void updateProfile_shouldReturn200AndUpdatedUser() throws Exception {
            UpdateProfileRequest request = new UpdateProfileRequest();
            request.setDisplayName("Vidyaaaaaaa");
            request.setBio("vidya'sssssssssssssssssss newBio");

            mockMvc.perform(put("/api/users/profile")
                            .header("Authorization", "Bearer " + jwt)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.displayName").value("newUser"))
                    .andExpect(jsonPath("$.bio").value("newBio"));
        }
    }
