package com.ProftaakS34.Opinion;

import com.ProftaakS34.Opinion.data.dao.UserDAO;
import com.ProftaakS34.Opinion.domain.service.UserServiceImplementation;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserDAOTests {
    public UserServiceImplementation userService;
    @BeforeEach
    public void setUp() {
        this.userService = new UserServiceImplementation(new MockUserRepo());
    }
    @Test
    public void checksIfPasswordExists() {
        UserDAO userDAOIncorrectPassword = new UserDAO();
        userDAOIncorrectPassword.setUsername("Username");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {userService.saveUser(userDAOIncorrectPassword);});
    }
    @Test
    public void checksIfUsernameExists() {
        UserDAO userDAOIncorrectUsername = new UserDAO();
        userDAOIncorrectUsername.setPassword("Password");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.saveUser(userDAOIncorrectUsername);
        });
    }
    @Test
    public void canSaveUser() {
        UserDAO correctUserDAO = new UserDAO("username", "password");
        userService.saveUser(correctUserDAO);
        Assertions.assertSame(userService.findAllUsers().get(0), correctUserDAO);
    }
}
