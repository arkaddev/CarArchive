package com.example.CarArchive.service;

import com.example.CarArchive.dto.CarResponse;
import com.example.CarArchive.dto.PartResponse;
import com.example.CarArchive.dto.UserRequest;
import com.example.CarArchive.dto.UserResponse;
import com.example.CarArchive.exception.UserNotFoundException;
import com.example.CarArchive.model.Car;
import com.example.CarArchive.model.Part;
import com.example.CarArchive.model.Role;
import com.example.CarArchive.model.User;
import com.example.CarArchive.repository.CarRepository;
import com.example.CarArchive.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
    private Car car;
    private Car car2;
    private Part part;
    private Part part2;
    private UserRequest userRequest;

    @BeforeEach
    void setup() {
        user = new User();
        user.setId(1L);
        user.setEmail("test1@test.com");
        user.setFirstname("testFirstname");
        user.setLastname("testLastname");
        user.setRole(Role.USER);

        car = new Car();
        car.setId(1L);
        car.setBrand("testBrand1");
        car.setUser(user);
        user.setCars(Arrays.asList(car));

        part = new Part();
        part.setId(1L);
        part.setName("testPart1");
        part.setCar(car);
        car.setParts(Arrays.asList(part));

        user2 = new User();
        user2.setId(2L);
        user2.setEmail("test2@test.com");
        user2.setRole(Role.USER);

        car2 = new Car();
        car2.setId(2L);
        car2.setBrand("testBrand2");
        car2.setUser(user2);
        user2.setCars(Arrays.asList(car2));

        part2 = new Part();
        part2.setId(2L);
        part2.setName("testPart2");
        part2.setCar(car);
        car2.setParts(Arrays.asList(part2));

        userRequest = new UserRequest();
        userRequest.setFirstname("testFirstname");
        userRequest.setLastname("testLastname");
        userRequest.setEmail("test@test.com");
    }

    @Test
    void getAllUsers() {
        List<User> users = Arrays.asList(user, user2);
        when(userRepository.findAll()).thenReturn(users);

        List<UserResponse> output = userService.getAllUsers();

        verify(userRepository, times(1)).findAll();
        assertEquals(2, userService.getAllUsers().size());

        assertEquals(1L, output.get(0).getId());
        assertEquals("test1@test.com", output.get(0).getEmail());
        assertEquals(1L, output.get(0).getCars().get(0).getOwnerId());
        assertEquals("testBrand1", output.get(0).getCars().get(0).getBrand());
        assertEquals(1L, output.get(0).getCars().get(0).getParts().get(0).getId());
        assertEquals("testPart1", output.get(0).getCars().get(0).getParts().get(0).getName());

        assertEquals(2L, output.get(1).getId());
        assertEquals("test2@test.com", output.get(1).getEmail());
        assertEquals(2L, output.get(1).getCars().get(0).getOwnerId());
        assertEquals("testBrand2", output.get(1).getCars().get(0).getBrand());
        assertEquals(2L, output.get(1).getCars().get(0).getParts().get(0).getId());
        assertEquals("testPart2", output.get(1).getCars().get(0).getParts().get(0).getName());
    }

    @Test
    void getUserById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UserResponse output = userService.getUserById(1L);

        verify(userRepository, times(1)).findById(1L);

        assertEquals(1L, output.getId());
        assertEquals("test1@test.com", output.getEmail());
        assertEquals(1L, output.getCars().get(0).getOwnerId());
        assertEquals("testBrand1", output.getCars().get(0).getBrand());
        assertEquals(1L, output.getCars().get(0).getParts().get(0).getId());
        assertEquals("testPart1", output.getCars().get(0).getParts().get(0).getName());
    }

//    @Test
//    void getUserByIdWhenUserDoesNotExist() {
//        when(userRepository.findById(3L)).thenReturn(Optional.empty());
//
//
//        UserResponse output = userService.getUserById(3L);
//        assertNull(output);
//    }

    @Test
    void addNewUser() {
        when(userRepository.save(any())).thenReturn(user);

        UserResponse output = userService.addNewUser(userRequest);

        verify(userRepository, times(1)).save(any(User.class));

        assertEquals("testFirstname", userService.addNewUser(userRequest).getFirstname());
        assertEquals("testLastname", userService.addNewUser(userRequest).getLastname());
        assertEquals("test@test.com", userService.addNewUser(userRequest).getEmail());
     }

    @Test
    void updateUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any())).thenReturn(user);

        UserResponse output = userService.updateUser(1L, userRequest);

        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(any());

        assertEquals(1L, output.getId());
        assertEquals("testFirstname", output.getFirstname());
        assertEquals("testLastname", output.getLastname());
        assertEquals("test@test.com", output.getEmail());
    }

    @Test
    void deleteUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        String output = userService.deleteUser(1L);

        verify(userRepository, times(1)).deleteById(1L);

        assertEquals("User 1 was deleted", output);
    }

    @Test
    void getUserByUsername() {
        when(userRepository.findByEmail("test1@test.com")).thenReturn(Optional.of(user));

        User output = userService.getUserByUsername("test1@test.com");

        verify(userRepository, times(1)).findByEmail("test1@test.com");

        assertEquals(1L, output.getId());
        assertEquals("testFirstname", output.getFirstname());
        assertEquals("testLastname", output.getLastname());
    }
}