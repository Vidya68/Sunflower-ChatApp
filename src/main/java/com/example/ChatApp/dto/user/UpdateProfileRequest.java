package com.example.ChatApp.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateProfileRequest {

    @NotBlank(message = "Display name cannot be empty")
    private String displayName;

    private String bio; // optional, can be empty
}
