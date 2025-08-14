package com.example.ChatApp.service.impl;

import com.example.ChatApp.dto.user.UpdateProfileRequest;
import com.example.ChatApp.dto.user.UserResponse;
import com.example.ChatApp.entity.User;
import com.example.ChatApp.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class) // Mockito + JUnit 5
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User testUser;

    @BeforeEach
    void setup() {
        testUser = new User();
        testUser.setId(1L);
        testUser.setUsername("oldUser");
        testUser.setBio("oldBio");
        testUser.setDisplayName("oldUser");
    }

    @Test
    void updateProfile_shouldUpdateDisplayNameAndBio() {
        // Arrange
        UpdateProfileRequest request = new UpdateProfileRequest();
        request.setDisplayName("newUser");
        request.setBio("newBio");

        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArgument(0));

        // Act
        UserResponse updated = userService.updateProfile(1L, request);

        // Assert
        assertEquals("newUser", updated.getDisplayName());
        assertEquals("newBio", updated.getBio());
    }
}
