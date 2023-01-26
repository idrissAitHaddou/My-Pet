package com.example.cvtheque.responces;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponce {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String image;
    private String description;
    private String role;
}
