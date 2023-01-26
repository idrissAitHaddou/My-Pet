package com.example.cvtheque.requests;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private Long id;
    private String firstName;
    private String lastName;
    @NotNull(message = "email is required")
    private String email;
    @NotNull(message = "password is required")
    private String password;
    private String image;
    private String description;
    private String role;
}
