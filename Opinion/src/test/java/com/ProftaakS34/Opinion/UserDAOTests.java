package com.ProftaakS34.Opinion;

import com.ProftaakS34.Opinion.Mocks.MockPasswordService;
import com.ProftaakS34.Opinion.Mocks.MockUserRepo;
import com.ProftaakS34.Opinion.data.dao.UserDAO;
import com.ProftaakS34.Opinion.domain.mapper.UserMapper;
import com.ProftaakS34.Opinion.domain.model.User;
import com.ProftaakS34.Opinion.domain.service.UserService;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserDAOTests {
    public UserService userService;

    @BeforeEach
    public void setUp() {
        this.userService = new UserService(new MockUserRepo(), new UserMapper(), new MockPasswordService());
    }


    @Test
    public void checksIfPasswordExists() {
        UserDAO userDAOIncorrectPassword = new UserDAO();
        userDAOIncorrectPassword.setUsername("Username");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.saveUser(userDAOIncorrectPassword.getUsername(),
                    userDAOIncorrectPassword.getEncryptedPassword());});
    }

    @Test
    public void checksIfUsernameExists() {
        UserDAO userDAOIncorrectUsername = new UserDAO();
        userDAOIncorrectUsername.setEncryptedPassword("Password");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.saveUser(userDAOIncorrectUsername.getUsername(),
                    userDAOIncorrectUsername.getEncryptedPassword());
        });
    }

    @Test
    public void canSaveUser() throws Exception {
        UserDAO correctUserDAO = new UserDAO("username", "password");
        userService.saveUser(correctUserDAO.getUsername(), correctUserDAO.getEncryptedPassword());

        User savedUser = userService.findAllUsers().get(0);

        Assertions.assertSame(savedUser.getUsername(), correctUserDAO.getUsername());
        Assertions.assertSame(savedUser.getPassword(), correctUserDAO.getEncryptedPassword());
    }
}
