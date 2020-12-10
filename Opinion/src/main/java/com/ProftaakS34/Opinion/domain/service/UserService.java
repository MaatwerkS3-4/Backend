package com.ProftaakS34.Opinion.domain.service;

import com.ProftaakS34.Opinion.data.dao.UserDAO;
import com.ProftaakS34.Opinion.data.repository.UserRepository;
import com.ProftaakS34.Opinion.domain.mapper.UserMapper;
import com.ProftaakS34.Opinion.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public User findUserById(Long id) {
        UserDAO userDAO = userRepository.findById(id).get();
        return userMapper.toModel(userDAO);
    }

    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        for (UserDAO dao : userRepository.findAll()){
            users.add(userMapper.toModel(dao));
        }
        return users;
    }

    public User saveUser(String username, String password) {
        if(password == null ||  password.isBlank()) throw new IllegalArgumentException("Password is null or empty");
        if(username == null || username.isBlank()) throw new IllegalArgumentException("Username is null or empty");

        UserDAO dao = userRepository.save(new UserDAO(username, password));
        return userMapper.toModel(dao);
    }
}
