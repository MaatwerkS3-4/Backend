package com.ProftaakS34.Opinion.domain.service;

import com.ProftaakS34.Opinion.data.dao.UserDAO;
import com.ProftaakS34.Opinion.data.repository.UserRepository;
import com.ProftaakS34.Opinion.domain.mapper.UserMapper;
import com.ProftaakS34.Opinion.domain.model.User;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordService passwordService;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordService passwordService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordService = passwordService;
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

    public User saveUser(String username, String password) throws Exception {
        if(password == null ||  password.isBlank()) throw new IllegalArgumentException("Password is null or empty");
        if(username == null || username.isBlank()) throw new IllegalArgumentException("Username is null or empty");
        String salt = passwordService.getNewSalt();
        String encryptedPassword = passwordService.getEncryptedPassword(password, salt);
        UserDAO dao = userRepository.save(new UserDAO(username, encryptedPassword, salt));
        return userMapper.toModel(dao);
    }
    public User logIn (String username, String password) throws Exception {
        if(password == null ||  password.isBlank()) throw new IllegalArgumentException("Password is null or empty");
        if(username == null || username.isBlank()) throw new IllegalArgumentException("Username is null or empty");
        UserDAO user = findUserByUsername(username);
        String passwordToCompare = passwordService.getEncryptedPassword(password, user.getSalt());
        if (!passwordToCompare.equals(user.getEncryptedPassword())) throw new IllegalArgumentException("Wrong Credentials");
        return userMapper.toModel(user);
    }
    private UserDAO findUserByUsername(String username) throws NotFoundException {
        for (UserDAO userDAO : userRepository.findAll()) {
            if (userDAO.getUsername().equals(username)) return userDAO;
        }
        throw new NotFoundException("User not in database");
    }
}
