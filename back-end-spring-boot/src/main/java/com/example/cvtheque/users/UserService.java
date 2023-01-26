package com.example.cvtheque.users;

import com.example.cvtheque.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDto userDto;

    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        return new  User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }

    public UserEntity loadUserByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email);
        return user;
    }

    public UserDto findUserById(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        UserDto userToDto = userDto.UserEntityToDto(user.get());
        return userToDto;
    }

    public Boolean updateUser(UserDto user) {
        try {
            UserEntity userEntity = userDto.UserDtoToEntity(user);
            userEntity.setPassword(this.loadUserByEmail(userEntity.getEmail()).getPassword());
            userRepository.save(userEntity);
            return true;
        }catch (Exception e) {
            throw new ApiException("user not updated", HttpStatus.CREATED);
        }
    }

}
