package com.example.cvtheque.authentication;

import com.example.cvtheque.security.config.JwtUtil;
import com.example.cvtheque.users.UserDto;
import com.example.cvtheque.users.UserEntity;
import com.example.cvtheque.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDto userDto;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    public ResponseEntity<?> signIn(UserDto userDto){
        Map<String, Object> responseToken = new HashMap<>();
        final UserDetails user = userService.loadUserByUsername(userDto.getEmail());
        if(user != null)
        {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword())
            );
            UserDto userInfo = userDto.UserEntityToDto(userService.loadUserByEmail(userDto.getEmail())) ;
            responseToken.put("accessToken", jwtUtil.generateToken(user));
            responseToken.put("user", userInfo);
            return ResponseEntity.ok(responseToken);
        }
        responseToken.put("accessToken", null);
        responseToken.put("user", null);
        return ResponseEntity.status(400).body(responseToken);
    }

}
