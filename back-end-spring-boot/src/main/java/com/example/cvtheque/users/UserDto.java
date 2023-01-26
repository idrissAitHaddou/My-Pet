package com.example.cvtheque.users;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String image;
    private String description;
    private String role;

    public UserDto UserEntityToDto(UserEntity user){
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .image(user.getImage())
                .role(user.getRole())
                .description(user.getDescription())
                .build();
    }

    public UserEntity UserDtoToEntity(UserDto userDto){
        UserEntity user = new UserEntity();
                user.setId(userDto.getId());
                user.setFirstName(userDto.getFirstName());
                user.setLastName(userDto.getLastName());
                user.setEmail(userDto.getEmail());
                user.setImage(userDto.getImage());
                user.setRole(userDto.getRole());
                user.setPassword(userDto.getPassword());
                user.setDescription(userDto.getDescription());
                return user;
    }

}
