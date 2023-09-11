package com.example.CarArchive.service;

import com.example.CarArchive.model.User;
import com.example.CarArchive.repository.CarRepository;
import com.example.CarArchive.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private User user;
    private User user2;

    @BeforeEach
    void setup() {

        user = new User();
        user.setId(1L);
        user.setEmail("test@test.com");

        user2 = new User();
        user2.setId(2L);
        user2.setEmail("test2@test.com");
    }
    @Test
    void getAllUsers() {

        List<User> users = Arrays.asList(user, user2);
        when(userRepository.findAll()).thenReturn(users);

        //verify(userRepository, times(1)).findAll();
        assertEquals(2, userService.getAllUsers().size());

        assertEquals(1L, userService.getAllUsers().get(0).getId());
        assertEquals("test@test.com", userService.getAllUsers().get(0).getEmail());

        assertEquals(2L, userService.getAllUsers().get(1).getId());
        assertEquals("test2@test.com", userService.getAllUsers().get(1).getEmail());

    }
}