package com.example.CarArchive.service;

import com.example.CarArchive.dto.CarRequest;
import com.example.CarArchive.dto.CarResponse;
import com.example.CarArchive.model.Car;
import com.example.CarArchive.model.User;
import com.example.CarArchive.repository.CarRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class CarServiceImplTest {

    @Autowired
    private CarService carService;

    @MockBean
    private CarRepository carRepository;

    private Car car;
    private Car car2;
    private CarRequest carRequest;

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

        car = new Car();
        car.setId(1L);
        car.setBrand("testBrand");
        car.setModel("testModel");
        car.setUser(user);

        car2 = new Car();
        car2.setId(2L);
        car2.setBrand("testBrand2");
        car2.setModel("testModel2");
        car2.setUser(user2);

        carRequest = new CarRequest();
        carRequest.setBrand("testBrand3");
        carRequest.setModel("testModel3");
        carRequest.setOwnerId(1L);


    }

    @Test
    void getAllCars() {
        List<Car> cars = Arrays.asList(car, car2);
        when(carRepository.findAll()).thenReturn(cars);

        List<CarResponse> serviceCars = carService.getAllCars();

        verify(carRepository, times(1)).findAll();
        assertEquals(2, serviceCars.size());

        assertEquals("testBrand", serviceCars.get(0).getBrand());
        assertEquals("testModel", serviceCars.get(0).getModel());
        assertEquals(1L, serviceCars.get(0).getOwnerId());

        assertEquals("testBrand2", serviceCars.get(1).getBrand());
        assertEquals("testModel2", serviceCars.get(1).getModel());
        assertEquals(2L, serviceCars.get(1).getOwnerId());
    }

    @Test
    void getCarById() {
        when(carRepository.findById(1L)).thenReturn(Optional.of(car));
        CarResponse serviceCar = carService.getCarById(1L);

        verify(carRepository, times(1)).findById(1L);

        assertEquals(1L, serviceCar.getId());
        assertEquals("testBrand", serviceCar.getBrand());
        assertEquals("testModel", serviceCar.getModel());
        assertEquals(1L, serviceCar.getOwnerId());
    }

//    @Test
//    void addNewCar() {
//        when(carRepository.save(any(Car.class))).thenReturn(car);
//        CarResponse serviceResponse = carService.addNewCar(carRequest);
//
//        verify(carRepository, times(1)).save(any(Car.class));
//        assertEquals("testBrand3", serviceResponse.getBrand());
//    }

//    @Test
//    void updateCar() {
//        when(carRepository.findById(1L)).thenReturn(Optional.of(car));
//        when(carRepository.save(any(Car.class))).thenReturn(car);
//
//        CarResponse serviceCar = carService.updateCar(1L, carRequest);
//
//        verify(carRepository, times(1)).findById(1L);
//        verify(carRepository, times(1)).save(any(Car.class));
//
//        assertEquals(1L, car.getId());
//        assertEquals("testBrand3", car.getBrand());
//        assertEquals("testModel3", car.getModel());
////        assertEquals("test2@test.com", car.getUser().getUsername());
//    }

    @Test
    void deleteCar() {
//        when(carRepository.findById(1L)).thenReturn(Optional.of(car));
//        verify(carRepository, times(1)).deleteById(1L);
    }

//    @Test
//    void getCarsByLoggedUsername() {
//        List<Car> cars = Arrays.asList(car);
//        when(carRepository.findCarsByUserId(1L)).thenReturn(cars);
//
//        List<CarResponse> serviceCars = carService.getCarsByLoggedUsername(user.getUsername());
//
//        verify(carRepository, times(1)).findCarsByUserId(1L);
//        assertEquals(1, serviceCars.size());
//
//        assertEquals("testBrand", serviceCars.get(0).getBrand());
//        assertEquals("testModel", serviceCars.get(0).getModel());
//        assertEquals(1L, serviceCars.get(0).getOwnerId());
//
//    }
}