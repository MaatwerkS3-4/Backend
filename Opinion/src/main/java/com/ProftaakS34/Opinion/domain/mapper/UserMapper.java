package com.ProftaakS34.Opinion.domain.mapper;

import com.ProftaakS34.Opinion.data.dao.UserDAO;
import com.ProftaakS34.Opinion.domain.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public UserDAO toDAO(User model){
        UserDAO dao = new UserDAO();
        dao.setId(model.getId());
        dao.setPassword(model.getPassword());
        dao.setUsername(model.getUsername());
        return dao;
    }

    public User toModel(UserDAO dao){
        User model = new User();
        model.setId(dao.getId());
        model.setPassword(dao.getPassword());
        model.setUsername(dao.getUsername());
        return model;
    }
}