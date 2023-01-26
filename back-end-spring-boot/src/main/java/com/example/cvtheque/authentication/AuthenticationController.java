package com.example.cvtheque.authentication;

import com.example.cvtheque.requests.UserRequest;
import com.example.cvtheque.security.config.JwtUtil;
import com.example.cvtheque.users.UserDto;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/v1/api/authentication")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody @Valid UserRequest userRequest) throws IOException {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userRequest, userDto);
        return authenticationService.signIn(userDto);
    }

    @PostMapping("/check-token")
    public ResponseEntity<Boolean> createAuthenticationToken(@RequestBody String token) throws IOException {
        try {
            Boolean expired = !jwtUtil.isTokenExpired(token);
            System.out.println(expired);
            return ResponseEntity.ok(expired);
        }catch (Exception e) {
            return ResponseEntity.ok(false);
        }
    }

}
