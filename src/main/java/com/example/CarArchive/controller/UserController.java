package com.example.CarArchive.controller;

import com.example.CarArchive.config.AuthenticationService;
import com.example.CarArchive.dto.UserResponse;
import com.example.CarArchive.model.Part;
import com.example.CarArchive.model.User;
import com.example.CarArchive.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    @Autowired
    public UserController(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @Operation(summary = "get all users", description = "")
    @GetMapping("/users")
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @Operation(summary = "get user by id", description = "")
    @GetMapping("/users/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }


    @Operation(summary = "add new user", description = "")
    @PostMapping("/users")
    public User addNewUser(@RequestBody User user) {
        return userService.addNewUser(user);
    }

    @Operation(summary = "get info about user", description = "")
    @GetMapping("/info")
    public String getInfo() {
        return authenticationService.getInfoAboutUser();
    }

    public String getLoggedUser() {
        return authenticationService.getInfoAboutUser();
    }
}


