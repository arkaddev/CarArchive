package com.example.CarArchive.service;

import com.example.CarArchive.dto.UserRequest;
import com.example.CarArchive.dto.UserResponse;
import com.example.CarArchive.exception.CarNotFoundException;
import com.example.CarArchive.exception.CarSaveException;
import com.example.CarArchive.exception.UserNotFoundException;
import com.example.CarArchive.exception.UserSaveException;
import com.example.CarArchive.mapper.UserMapper;
import com.example.CarArchive.model.Role;
import com.example.CarArchive.model.User;
import com.example.CarArchive.repository.UserRepository;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    public final UserRepository userRepository;
    public final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::userToUserResponse)
                .toList();
    }

    @Override
    public UserResponse getUserById(Long id) {
        Optional<UserResponse> optionalUserResponse = userRepository.findById(id).map(userMapper::userToUserResponse);
        return optionalUserResponse.orElseThrow(()-> new UserNotFoundException("User does not exist"));
    }

    @Override
    public UserResponse addNewUser(UserRequest userRequest) {
        User user = userMapper.userRequestToUser(userRequest);
        user.setRole(Role.USER);
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new UserSaveException("User cannot be saved");
        }
        return new UserResponse(user.getId(), user.getFirstname(), user.getLastname(), user.getEmail());
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest userRequest) {
        Optional<User> optionalUserResponse = userRepository.findById(id);
        User user = optionalUserResponse.orElseThrow(()->new CarNotFoundException("User does not exist"));

        user.setFirstname(userRequest.getFirstname());
        user.setLastname(userRequest.getLastname());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new UserSaveException("User cannot be saved");
        }
        return new UserResponse(user.getId(), user.getFirstname(), user.getLastname(), user.getEmail());
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByEmail(username).orElseThrow();
    }
}
