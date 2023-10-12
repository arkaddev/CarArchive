package com.example.CarArchive.service;

import com.example.CarArchive.dto.CarRequest;
import com.example.CarArchive.dto.CarResponse;
import com.example.CarArchive.exception.CarNotFoundException;
import com.example.CarArchive.model.Car;
import com.example.CarArchive.model.Part;
import com.example.CarArchive.model.Role;
import com.example.CarArchive.model.User;
import com.example.CarArchive.repository.CarRepository;
import com.example.CarArchive.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class CarServiceImplTest {

    @Autowired
    private CarService carService;

    @MockBean
    private CarRepository carRepository;

    @MockBean
    private UserRepository userRepository;

    private User user;
    private User user2;
    private Car car;
    private Car car2;
    private Part part;
    private Part part2;


    private CarRequest carRequest;

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

        carRequest = new CarRequest();
        carRequest.setBrand("testBrandCarRequest1");
        carRequest.setModel("testModelCarRequest1");
        carRequest.setOwnerId(1L);
    }

    @Test
    void getAllCars() {
        List<Car> cars = Arrays.asList(car, car2);
        when(carRepository.findAll()).thenReturn(cars);

        List<CarResponse> output = carService.getAllCars();

        verify(carRepository, times(1)).findAll();
        assertEquals(2, output.size());

        assertEquals("testBrand1", output.get(0).getBrand());
        assertEquals(1L, output.get(0).getOwnerId());

        assertEquals("testBrand2", output.get(1).getBrand());
        assertEquals(2L, output.get(1).getOwnerId());
    }

    @Test
    void getCarById() {
        when(carRepository.findById(1L)).thenReturn(Optional.of(car));

        CarResponse output = carService.getCarById(1L);

        verify(carRepository, times(1)).findById(1L);

        assertEquals(1L, output.getId());
        assertEquals("testBrand1", output.getBrand());
        assertEquals(1L, output.getOwnerId());
    }

    @Test
    void getCarByIdWhenCarDoesNotExist() {
        when(carRepository.findById(3L)).thenReturn(Optional.empty());

        try {
            CarResponse output = carService.getCarById(3L);
        } catch (CarNotFoundException e) {
            assertEquals("Car does not exist", e.getMessage());
        }
    }

    @Test
    void addNewCar() {
        when(carRepository.save(any(Car.class))).thenReturn(car);

        CarResponse output = carService.addNewCar(carRequest);

        verify(carRepository, times(1)).save(any(Car.class));

        assertEquals("testBrandCarRequest1", output.getBrand());
        assertEquals("testModelCarRequest1", output.getModel());
        assertEquals(1L, output.getOwnerId());
    }

    @Test
    void updateCar() {
        when(carRepository.findById(1L)).thenReturn(Optional.of(car));
        when(carRepository.save(any(Car.class))).thenReturn(car);

        CarResponse output = carService.updateCar(1L, carRequest);

        verify(carRepository, times(1)).findById(1L);
        verify(carRepository, times(1)).save(any(Car.class));

        assertEquals(1L, output.getId());
        assertEquals("testBrandCarRequest1", output.getBrand());
        assertEquals("testModelCarRequest1", output.getModel());
//        assertEquals("test2@test.com", car.getUser().getUsername());
    }

    @Test
    void deleteCar() {
        when(carRepository.findById(1L)).thenReturn(Optional.of(car));

        String output = carService.deleteCar(1L);

        verify(carRepository, times(1)).deleteById(1L);

        assertEquals("Car 1 was deleted", output);
    }

    @Test
    void getCarByIdByLoggedUsername() {
        when(carRepository.findById(1L)).thenReturn(Optional.of(car));
        when(userRepository.findByEmail("test1@test.com")).thenReturn(Optional.of(user));

        CarResponse output = carService.getCarByIdByLoggedUsername(1L, "test1@test.com");

        //verify(carRepository, times(1)).findById(1L);

        assertEquals(1L, output.getId());
        assertEquals("testBrand1", output.getBrand());
        assertEquals(1L, output.getOwnerId());
    }

    @Test
    void getCarByIdByLoggedUsernameWhenCarDoesNotExist() {
        when(carRepository.findById(3L)).thenReturn(Optional.empty());
        when(userRepository.findByEmail("test1@test.com")).thenReturn(Optional.of(user));

        try {
            CarResponse output = carService.getCarByIdByLoggedUsername(3L, "test1@test.com");
        } catch (CarNotFoundException e) {
            assertEquals("Car does not exist", e.getMessage());
        }
    }
//    @Test
//    void getCarsByLoggedUsername() {
//        List<Car> cars = Arrays.asList(car);
//        when(carRepository.findCarsByUserId(1L)).thenReturn(cars);
//        List<CarResponse> output = carService.getCarsByLoggedUsername(user.getUsername());

//        verify(carRepository, times(1)).findCarsByUserId(1L);
//        assertEquals(1, serviceCars.size());
//
//        assertEquals("testBrand", serviceCars.get(0).getBrand());
//        assertEquals("testModel", serviceCars.get(0).getModel());
//        assertEquals(1L, serviceCars.get(0).getOwnerId());
//    }
}