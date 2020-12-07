package com.ProftaakS34.Opinion.domain.service;

import com.ProftaakS34.Opinion.data.dao.UserDAO;
import com.ProftaakS34.Opinion.data.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDAO findUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<UserDAO> findAllUsers() {
        return (List<UserDAO>) userRepository.findAll();
    }

    @Override
    public UserDAO saveUser(UserDAO userDAO) {
        if(userDAO.getPassword() == null ||  userDAO.getPassword().isBlank()) throw new IllegalArgumentException("Password is null or empty");
        if(userDAO.getUsername() == null || userDAO.getUsername().isBlank()) throw new IllegalArgumentException("Username is null or empty");
        return userRepository.save(userDAO);
    }
}
