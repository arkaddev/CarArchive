package com.example.CarArchive.service;

import com.example.CarArchive.dto.PartResponse;
import com.example.CarArchive.mapper.PartMapper;
import com.example.CarArchive.model.Car;
import com.example.CarArchive.model.Part;
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
import static org.mockito.Mockito.when;

@SpringBootTest
class PartServiceImplTest {

    private final PartMapper partMapper;

    @Autowired
    public PartServiceImplTest(PartMapper partMapper) {
        this.partMapper = partMapper;
    }

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

        car = new Car();
        car.setId(1L);
        car.setUser(user);

        part = new Part();
        part.setId(1L);
        part.setName("testPart1");
        part.setCar(car);

        user2 = new User();
        user2.setId(2L);

        car2 = new Car();
        car2.setId(2L);
        car2.setUser(user2);

        part2 = new Part();
        part2.setId(2L);
        part2.setName("testPart2");
        part2.setCar(car);

        partResponse = new PartResponse();
        partResponse.setId(1L);
        partResponse.setName("testPartResponse1");
    }

    @Test
    void getAllParts() {
        List<Part> parts = Arrays.asList(part, part2);

        when(partRepository.findAll()).thenReturn(parts);
        List<PartResponse> output = partService.getAllParts();
        assertEquals("testPart1", output.get(0).getName());
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