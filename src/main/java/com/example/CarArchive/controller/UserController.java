package com.example.CarArchive.controller;

import com.example.CarArchive.config.AuthenticationService;
import com.example.CarArchive.dto.UserRequest;
import com.example.CarArchive.dto.UserResponse;
import com.example.CarArchive.model.Part;
import com.example.CarArchive.model.User;
import com.example.CarArchive.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

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
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @Operation(summary = "get user by id", description = "")
    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @Operation(summary = "add new user", description = "")
    @PostMapping("/users")
    public ResponseEntity<UserResponse> addNewUser(@RequestBody UserRequest userRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addNewUser(userRequest));
    }

    @Operation(summary = "update user by id", description = "")
    @PutMapping("/users/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest){
        return ResponseEntity.ok(userService.updateUser(id, userRequest));
    }

    @Operation(summary = "delete user by id", description = "")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(userService.deleteUser(id));
    }

    @Operation(summary = "get info about user", description = "")
    @GetMapping("/users/info")
    public String getInfo() {
        return authenticationService.getInfoAboutUser();
    }

    public String getLoggedUser() {
        return authenticationService.getInfoAboutUser();
    }
}


