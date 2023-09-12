package com.example.CarArchive.service;

import com.example.CarArchive.dto.UserResponse;
import com.example.CarArchive.model.User;
import com.example.CarArchive.repository.UserRepository;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long id);

    User addNewUser(User user);

    User getUserByUsername(String username);
}
