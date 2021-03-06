package com.ProftaakS34.Opinion;

import com.ProftaakS34.Opinion.Mocks.MockPasswordService;
import com.ProftaakS34.Opinion.Mocks.MockUserRepo;
import com.ProftaakS34.Opinion.data.dao.UserDAO;
import com.ProftaakS34.Opinion.domain.mapper.UserMapper;
import com.ProftaakS34.Opinion.domain.model.User;
import com.ProftaakS34.Opinion.domain.service.PasswordService;
import com.ProftaakS34.Opinion.domain.service.UserService;
import javassist.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserServiceTests {
    private UserService userService;

    @BeforeEach
    void setUp() {
        PasswordService passwordService = new PasswordService();
        this.userService = new UserService(new MockUserRepo(), new UserMapper(), passwordService);
    }

    @Test
    void UsernameNotExists() {
        Assertions.assertThrows(NotFoundException.class, () -> {
            userService.logIn("FakeName", "FakePassword");
        });
    }

    @Test
    void CheckForWrongPassword() throws Exception {
        UserDAO correctUserDAO = new UserDAO("username", "password");
        userService.saveUser(correctUserDAO.getUsername(), correctUserDAO.getEncryptedPassword());

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.logIn(correctUserDAO.getUsername(), "FakePassword");
        });
    }


    @Test
    void canLogIn() throws Exception {
        UserDAO correctUserDAO = new UserDAO("username", "password");
        User correctUser = userService.saveUser(correctUserDAO.getUsername(), correctUserDAO.getEncryptedPassword());

        User loggedIn = userService.logIn(correctUserDAO.getUsername(), correctUserDAO.getEncryptedPassword());

        Assertions.assertSame(loggedIn.getUsername(), correctUser.getUsername());
        Assertions.assertSame(loggedIn.getPassword(), correctUser.getPassword());
    }


}
