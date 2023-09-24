package com.example.CarArchive.service;

import com.example.CarArchive.dto.PartResponse;
import com.example.CarArchive.mapper.PartMapper;
import com.example.CarArchive.model.Car;
import com.example.CarArchive.model.Part;
import com.example.CarArchive.model.Role;
import com.example.CarArchive.model.User;
import com.example.CarArchive.repository.CarRepository;
import com.example.CarArchive.repository.PartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class PartServiceImplTest {

    @Autowired
    private PartService partService;

    @MockBean
    private PartRepository partRepository;

    private User user;
    private User user2;
    private Car car;
    private Car car2;
    private Part part;
    private Part part2;

    private PartResponse partResponse;
    private PartResponse partResponse2;


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
    }

    @Test
    void addNewPart() {
    }

    @Test
    void updatePart() {
    }

    @Test
    void deletePart() {
    }

    @Test
    void getPartsToExchangeByMileage() {
    }
}