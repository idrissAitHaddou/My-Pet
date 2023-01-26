package com.example.cvtheque.users;

import com.example.cvtheque.requests.UserRequest;
import com.example.cvtheque.responces.UserResponce;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserResponce> getUserProfile(@RequestParam(value = "id") Long id) {
        UserDto userDto = userService.findUserById(id);
        UserResponce userResponce = new UserResponce();
        BeanUtils.copyProperties(userDto, userResponce);
        return ResponseEntity.ok(userResponce);
    }
    @PostMapping("/profile/update")
    public ResponseEntity<Boolean> getUserProfile(@RequestBody @Valid UserRequest userRequest) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userRequest, userDto);
        Boolean status = userService.updateUser(userDto);
        return ResponseEntity.ok(status);
    }
}
