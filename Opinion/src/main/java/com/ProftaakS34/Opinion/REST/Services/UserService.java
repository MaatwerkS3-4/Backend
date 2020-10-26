package com.ProftaakS34.Opinion.REST.Services;

import com.ProftaakS34.Opinion.Data.Entities.User;

import java.util.List;

public interface UserService {

    User findUserById(Long id);

    List<User> findAllUsers();

    User saveUser(User user);
}
