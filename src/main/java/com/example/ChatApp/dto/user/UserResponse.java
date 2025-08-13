package com.example.ChatApp.dto.user;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class UserResponse {
    private Long id;
    private String email;
    private String username;
    private String displayName;
    private String profilePicture;
    private String bio;
    private LocalDateTime createdAt;
}
