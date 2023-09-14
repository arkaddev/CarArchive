package com.example.CarArchive.service;

import com.example.CarArchive.dto.UserRequest;
import com.example.CarArchive.dto.UserResponse;
import com.example.CarArchive.model.User;
import com.example.CarArchive.repository.UserRepository;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long id);

    UserResponse addNewUser(UserRequest userRequest);

    UserResponse updateUser(Long id, UserRequest userRequest);

    String deleteUser(Long id);

    User getUserByUsername(String username);
}
