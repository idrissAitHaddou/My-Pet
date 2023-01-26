package com.example.cvtheque.users;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserServiceTest {
    @Autowired
    private UserRepository userRepository;
    private UserDto userDto;

    @BeforeEach
    public void setUp() throws Exception {
        userDto = new UserDto();
    }

    @Test
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"idriss@gmail.com", "ali@gmail.com"})
    void loadUserByUsername(String email) {
        UserEntity user = userRepository.findByEmail(email);
        User user1 = new User(user.getEmail(), user.getPassword(), new ArrayList<>());
        Assertions.assertNotNull(user1);
    }

    @Test
    @ParameterizedTest
    @ValueSource(ints = {1,2,3})
    void findUserById(int id) {
        Long idUser = new Long(id);
        Optional<UserEntity> user = userRepository.findById(idUser);
        UserDto userToDto = userDto.UserEntityToDto(user.get());
        Assertions.assertNotNull(userToDto);
    }
}