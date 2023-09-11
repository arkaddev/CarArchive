package com.example.CarArchive.service;

import com.example.CarArchive.dto.UserResponse;
import com.example.CarArchive.model.User;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUsers();
    User addNewUser(User user);

    User getUserByUsername(String username);
}
