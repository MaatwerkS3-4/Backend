package com.ProftaakS34.Opinion.web.api.controller;

import com.ProftaakS34.Opinion.data.dao.UserDAO;
import com.ProftaakS34.Opinion.domain.mapper.UserMapper;
import com.ProftaakS34.Opinion.domain.model.User;
import com.ProftaakS34.Opinion.domain.service.UserService;
import com.ProftaakS34.Opinion.web.api.dto.CreateUserDTO;
import com.ProftaakS34.Opinion.web.api.dto.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> resource = new ArrayList<>();
        for(User u : userService.findAllUsers()){
            resource.add(userMapper.toDTO(u));
        }

        return ResponseEntity.ok(resource);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable long userId){
        UserDTO resource = userMapper.toDTO(userService.findUserById(userId));
        return ResponseEntity.ok(resource);
    }

    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@RequestBody CreateUserDTO dto){
        User user = userService.saveUser(dto.getUsername(), dto.getPassword());
        UserDTO resource = userMapper.toDTO(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(resource);
    }
}
