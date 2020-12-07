package com.ProftaakS34.Opinion.domain.service;

import com.ProftaakS34.Opinion.data.dao.UserDAO;

import java.util.List;

public interface UserService {

    UserDAO findUserById(Long id);

    List<UserDAO> findAllUsers();

    UserDAO saveUser(UserDAO userDAO);
}
