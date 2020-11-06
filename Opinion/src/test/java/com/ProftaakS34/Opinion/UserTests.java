package com.ProftaakS34.Opinion;

import com.ProftaakS34.Opinion.Data.Entities.User;
import com.ProftaakS34.Opinion.Data.Repositories.UserRepository;
import com.ProftaakS34.Opinion.REST.Services.UserService;
import com.ProftaakS34.Opinion.REST.Services.UserServiceImplementation;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.function.Executable;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTests {
    public UserServiceImplementation userService;
    @BeforeEach
    public void setUp() {
        this.userService = new UserServiceImplementation(new MockUserRepo());
    }
    @Test
    public void checksIfPasswordExists() {
        User userIncorrectPassword = new User();
        userIncorrectPassword.setUsername("Username");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {userService.saveUser(userIncorrectPassword);});
    }
    @Test
    public void checksIfUsernameExists() {
        User userIncorrectUsername = new User();
        userIncorrectUsername.setPassword("Password");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.saveUser(userIncorrectUsername);
        });
    }
    @Test
    public void canSaveUser() {
        User correctUser = new User("username", "password");
        Assertions.assertNotNull(userService.saveUser(correctUser));
    }
}
