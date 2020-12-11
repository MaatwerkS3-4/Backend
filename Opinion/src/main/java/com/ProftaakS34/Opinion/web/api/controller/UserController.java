package com.ProftaakS34.Opinion.web.api.controller;

import com.ProftaakS34.Opinion.authentication.AuthenticationService;
import com.ProftaakS34.Opinion.domain.mapper.UserMapper;
import com.ProftaakS34.Opinion.domain.model.User;
import com.ProftaakS34.Opinion.domain.service.UserService;
import com.ProftaakS34.Opinion.web.api.dto.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
@Api(tags = "Users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final AuthenticationService authservice;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper, AuthenticationService authService) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.authservice = authService;
    }

    /**
     * Retrieves info about all existing users and returns it as a {@link List<UserDTO>}
     *
     * @return The {@link ResponseEntity} with status {@code 200 (OK)} with as body the list of found users {@link List<UserDTO>}
     */
    @ApiOperation(
            value = "Get info about all existing users"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok - the list of users has been returned")
    })
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> resource = new ArrayList<>();
        for(User u : userService.findAllUsers()){
            resource.add(userMapper.toDTO(u));
        }

        return ResponseEntity.ok(resource);
    }

    /**
     * Retrieves info about a user by it's id and returns it as a {@link UserDTO}
     *
     * @param userId The id of the user for which to retrieve info
     * @return The {@link ResponseEntity} with status {@code 200 (OK)} with as body the found user {@link UserDTO}
     */
    @ApiOperation(
            value = "Get info about a specific user by it's id"
    )
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Ok - the info about the specific user has been returned")
    })
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable long userId){
        UserDTO resource = userMapper.toDTO(userService.findUserById(userId));
        return ResponseEntity.ok(resource);
    }

    /**
     * Create a new user
     *
     * @param dto The {@link CreateUserDTO} which contains the information to create the user
     * @return The {@link ResponseEntity} with status {@code 201 (CREATED)} with as body the created user {@link DiscussionDTO}
     */
    @ApiOperation(
            value = "Create a new user"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created - user has been created")
    })
    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@RequestBody CreateUserDTO dto){
        User user = userService.saveUser(dto.getUsername(), dto.getPassword());
        UserDTO resource = userMapper.toDTO(user);
        String jwt = authservice.authorizeUserLogin(dto.getUsername());
        resource.setJwt(jwt);
        return ResponseEntity.status(HttpStatus.CREATED).body(resource);
    }
}
