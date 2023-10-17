package com.example.CarArchive.service;

import com.example.CarArchive.dto.CarResponse;
import com.example.CarArchive.dto.PartRequest;
import com.example.CarArchive.dto.PartResponse;
import com.example.CarArchive.exception.CarNotFoundException;
import com.example.CarArchive.exception.PartNotFoundException;
import com.example.CarArchive.model.Car;
import com.example.CarArchive.model.Part;
import com.example.CarArchive.model.Role;
import com.example.CarArchive.model.User;
import com.example.CarArchive.repository.PartRepository;
import com.example.CarArchive.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class PartServiceImplTest {

    @Autowired
    private PartService partService;

    @MockBean
    private PartRepository partRepository;

    @MockBean
    private UserRepository userRepository;

    private User user;
    private User user2;
    private Car car;
    private Car car2;
    private Part part;
    private Part part2;
    private PartRequest partRequest;


    @BeforeEach
    void setup() {
        user = new User();
        user.setId(1L);
        user.setEmail("test1@test.com");
        user.setFirstname("testFirstname");
        user.setLastname("testLastname");

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
        part2.setCar(car2);
        car2.setParts(Arrays.asList(part2));

        partRequest = new PartRequest();
        partRequest.setName("testNamePartRequest1");
        partRequest.setCarId(5L);
        partRequest.setUserId(10L);
    }

    @Test
    void getAllParts() {
        List<Part> parts = Arrays.asList(part, part2);
        when(partRepository.findAll()).thenReturn(parts);

        List<PartResponse> output = partService.getAllParts();

        verify(partRepository, times(1)).findAll();
        assertEquals(2, output.size());

        assertEquals(1L, output.get(0).getId());
        assertEquals("testPart1", output.get(0).getName());
        assertEquals(1L, output.get(0).getCarId());
        assertEquals(1L, output.get(0).getUserId());

        assertEquals(2L, output.get(1).getId());
        assertEquals("testPart2", output.get(1).getName());
        assertEquals(2L, output.get(1).getCarId());
        assertEquals(2L, output.get(1).getUserId());
    }

    @Test
    void getPartById() {
        when(partRepository.findById(1L)).thenReturn(Optional.of(part));

        PartResponse output = partService.getPartById(1L);

        verify(partRepository, times(1)).findById(1L);

        assertEquals(1L, output.getId());
        assertEquals("testPart1", output.getName());
        assertEquals(1L, output.getCarId());
        assertEquals(1L, output.getUserId());
    }

    @Test
    void getPartByIdWhenPartDoesNotExist() {
        when(partRepository.findById(3L)).thenReturn(Optional.empty());

        try {
            PartResponse output = partService.getPartById(3L);
        } catch (PartNotFoundException e) {
            assertEquals("Part does not exist", e.getMessage());
        }
    }

    @Test
    void addNewPart() {
        when(partRepository.save(any(Part.class))).thenReturn(part);

        PartResponse output = partService.addNewPart(partRequest);

        verify(partRepository, times(1)).save(any(Part.class));

        assertEquals("testNamePartRequest1", output.getName());
        assertEquals(5L, output.getCarId());
        assertEquals(10L, output.getUserId());
    }

    @Test
    void updatePart() {
        when(partRepository.findById(1L)).thenReturn(Optional.of(part));
        when(partRepository.save(any(Part.class))).thenReturn(part);

        PartResponse output = partService.updatePart(1L, partRequest);

        verify(partRepository, times(1)).findById(1L);
        verify(partRepository, times(1)).save(any(Part.class));

        assertEquals("testNamePartRequest1", output.getName());
        assertEquals(5L, output.getCarId());
        assertEquals(10L, output.getUserId());
    }

    @Test
    void deletePart() {
        when(partRepository.findById(1L)).thenReturn(Optional.of(part));

        String output = partService.deletePart(1L);

        verify(partRepository, times(1)).deleteById(1L);

        assertEquals("Part 1 was deleted", output);
    }

    @Test
    void getPartsToExchangeByMileage() {
    }

    @Test
    void getAllPartsByLoggedUsername() {
        List<Part> parts = Arrays.asList(part);
        when(partRepository.findAll()).thenReturn(parts);
        when(userRepository.findByEmail("test1@test.com")).thenReturn(Optional.of(user));

        List<PartResponse> output = partService.getAllPartsByLoggedUsername("test1@test.com");

        verify(partRepository, times(1)).findAll();
        //assertEquals(2, output.size());

        assertEquals(1L, output.get(0).getId());
        assertEquals("testPart1", output.get(0).getName());
        assertEquals(1L, output.get(0).getCarId());
        assertEquals(1L, output.get(0).getUserId());

    }

    @Test
    void getPartByIdByLoggedUsername() {
        when(partRepository.findById(1L)).thenReturn(Optional.of(part));
        when(userRepository.findByEmail("test1@test.com")).thenReturn(Optional.of(user));

        PartResponse output = partService.getPartByIdByLoggedUsername(1L, "test1@test.com");

        verify(partRepository, times(1)).findById(1L);

        assertEquals(1L, output.getId());
        assertEquals("testPart1", output.getName());
        assertEquals(1L, output.getCarId());
        assertEquals(1L, output.getUserId());
    }

    @Test
    void addNewPartByLoggedUsername() {
        when(partRepository.save(any(Part.class))).thenReturn(part);
        when(userRepository.findByEmail("test1@test.com")).thenReturn(Optional.of(user));

        PartResponse output = partService.addNewPartByLoggedUsername(partRequest, "test1@test.com");

        verify(partRepository, times(1)).save(any(Part.class));

        assertEquals("testNamePartRequest1", output.getName());
        assertEquals(5L, output.getCarId());
        assertEquals(1L, output.getUserId());
    }

    @Test
    void updatePartByLoggedUsername() {
        when(partRepository.findById(1L)).thenReturn(Optional.of(part));
        when(partRepository.save(any(Part.class))).thenReturn(part);
        when(userRepository.findByEmail("test1@test.com")).thenReturn(Optional.of(user));

        PartResponse output = partService.updatePartByLoggedUsername(1L, partRequest, "test1@test.com");

//        verify(partRepository, times(1)).findById(1L);
//        verify(partRepository, times(1)).save(any(Part.class));

        assertEquals("testNamePartRequest1", output.getName());
        assertEquals(5L, output.getCarId());
        assertEquals(1L, output.getUserId());
    }
}