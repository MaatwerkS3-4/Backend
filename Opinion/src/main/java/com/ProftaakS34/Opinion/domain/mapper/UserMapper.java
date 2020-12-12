package com.ProftaakS34.Opinion.domain.mapper;

import com.ProftaakS34.Opinion.data.dao.UserDAO;
import com.ProftaakS34.Opinion.domain.model.User;
import com.ProftaakS34.Opinion.web.api.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public UserDAO toDAO(User model){
        UserDAO dao = new UserDAO();
        dao.setId(model.getId());
        dao.setEncryptedPassword(model.getPassword());
        dao.setUsername(model.getUsername());
        return dao;
    }

    public User toModel(UserDAO dao){
        User model = new User();
        model.setId(dao.getId());
        model.setPassword(dao.getEncryptedPassword());
        model.setUsername(dao.getUsername());
        return model;
    }

    public UserDTO toDTO(User user){
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        return dto;
    }
}
