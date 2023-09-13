package com.example.CarArchive.mapper;

import com.example.CarArchive.dto.CarResponse;
import com.example.CarArchive.dto.UserRequest;
import com.example.CarArchive.dto.UserResponse;
import com.example.CarArchive.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse userToUserResponse(User user);

    User userRequestToUser(UserRequest userRequest);
}
