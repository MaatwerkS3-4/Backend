package com.ProftaakS34.Opinion;

import com.ProftaakS34.Opinion.data.dao.UserDAO;
import com.ProftaakS34.Opinion.domain.mapper.UserMapper;
import com.ProftaakS34.Opinion.domain.model.User;
import com.ProftaakS34.Opinion.web.api.dto.UserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserMapperTests {
    private User user;
    private UserDAO userDAO;
    private UserDTO userDTO;
    private UserMapper mapper;

    private UserMapperTests() {
        userDAO = new UserDAO();
        userDAO.setId(1);
        userDAO.setUsername("username");
        userDAO.setEncryptedPassword("Password");

        userDTO = new UserDTO();
        userDTO.setId(1);
        userDTO.setUsername("username");

        user = new User();
        user.setId(1);
        user.setUsername("username");
        user.setPassword("Password");
    }

    @BeforeEach
    void SetUp() {
        mapper = new UserMapper();
    }


    @Test
    void ToDAOTest() {
        UserDAO newDAO = mapper.toDAO(user);

        Assertions.assertEquals(userDAO.getId(), newDAO.getId());
        Assertions.assertEquals(userDAO.getUsername(), newDAO.getUsername());
        Assertions.assertEquals(userDAO.getEncryptedPassword(), newDAO.getEncryptedPassword());

    }

    @Test
    void toModelTest() {
        User newUser = mapper.toModel(userDAO);

        Assertions.assertEquals(user.getId(), newUser.getId());
        Assertions.assertEquals(user.getUsername(), newUser.getUsername());
        Assertions.assertEquals(user.getPassword(), newUser.getPassword());
    }

    @Test
    void toDTOTest() {
        UserDTO newDTO = mapper.toDTO(user);

        Assertions.assertEquals(userDTO.getId(), newDTO.getId());
        Assertions.assertEquals(userDTO.getUsername(), newDTO.getUsername());
    }

}
