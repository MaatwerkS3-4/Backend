package com.ProftaakS34.Opinion.REST.Services;

import com.ProftaakS34.Opinion.Data.Entities.User;
import com.ProftaakS34.Opinion.Data.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> findAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        if(user.getPassword() == null ||  user.getPassword().isBlank()) throw new IllegalArgumentException("Password is null or empty");
        if(user.getUsername() == null || user.getUsername().isBlank()) throw new IllegalArgumentException("Username is null or empty");
        return userRepository.save(user);
    }
}
