package com.example.ChatApp.dto.user;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;

    private String displayName;
}


//@Data
//public class RegisterRequest {
//    @Email
//    @NotBlank
//    private String email;
//
//    @NotBlank
//    private String username;
//
//    @NotBlank
//    private String password;
//
//    private String displayName;
//}
