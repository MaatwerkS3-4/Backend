package com.ProftaakS34.Opinion.web.api.controller;

import com.ProftaakS34.Opinion.data.dao.UserDAO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UserController.BASE_URL)
public class UserController {

    public static final String BASE_URL = "api/users";
//
//    private final UserService userService;
//
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping
//    public List<UserDAO> getAllUsers(){
//        return userService.findAllUsers();
//    }
//
//    @GetMapping("/id/{id}")
//    public UserDAO getUserById(@PathVariable Long id){
//        return userService.findUserById(id);
//    }
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public UserDAO saveUser(@RequestBody UserDAO userDAO){
//        return userService.saveUser(userDAO);
//    }
}
