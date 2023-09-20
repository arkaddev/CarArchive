package com.example.CarArchive.mapper;

import com.example.CarArchive.dto.UserRequest;
import com.example.CarArchive.dto.UserResponse;
import com.example.CarArchive.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMapperImpl implements UserMapper {

    private final CarMapper carMapper;

    @Autowired
    public UserMapperImpl(CarMapper carMapper) {
        this.carMapper = carMapper;
    }

    @Override
    public UserResponse userToUserResponse(User user) {

        UserResponse userResponse = new UserResponse();

        userResponse.setId(user.getId());
        userResponse.setFirstname(user.getFirstname());
        userResponse.setLastname(user.getLastname());
        userResponse.setEmail(user.getEmail());
        userResponse.setCars(user.getCars().stream().map(carMapper::carToCarResponse).toList());

        return userResponse;
    }

    @Override
    public User userRequestToUser(UserRequest userRequest) {
        return null;
    }
}
