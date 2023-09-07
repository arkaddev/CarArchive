package com.example.CarArchive.controller;

import com.example.CarArchive.model.Part;
import com.example.CarArchive.model.User;
import com.example.CarArchive.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "get all users", description = "")
    @GetMapping("/users")
    public List<User> getAllParts() {
        return userService.getAllUsers();
    }
}

