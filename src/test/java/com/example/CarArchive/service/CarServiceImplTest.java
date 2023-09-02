package com.example.CarArchive.service;

import com.example.CarArchive.model.Car;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class CarServiceImplTest {

    @Autowired
    private CarService carService;

    @MockBean
    private CarRepository carRepository;

    private Car car;
    private Car car2;

    @BeforeEach
    void setup() {
        // przygotowanie mocka, repozytorium
        car = new Car();
        car.setId(1L);
        car.setBrand("testBrand");
        car.setModel("testModel");
        car.setOwner("testOwner");


        car2 = new Car();
        car2.setId(2L);
        car2.setBrand("testBrand2");
        car2.setModel("testModel2");
        car2.setOwner("testOwner2");
    }

    @Test
    void getAllCars() {
        List<Car> cars = Arrays.asList(car, car2);

        when(carRepository.findAll()).thenReturn(cars);
        // pobranie samochodow za pomoca serwisu
        List<Car> serviceCars = carService.getAllCars();

        assertThat(serviceCars).isNotEmpty();
        assertThat(serviceCars).hasSize(2);
    }

    @Test
    void getCarById() {
        when(carRepository.findById(1L)).thenReturn(Optional.of(car));
        Car serviceCar = carService.getCarById(1L);

        assertEquals(serviceCar.getBrand(), "testBrand");
        assertEquals(serviceCar.getId(), 1);

        assertThat(serviceCar).isNotNull();
        assertThat(serviceCar.getId()).isEqualTo(1L);
    }

    @Test
    void addNewCar() {
    }

    @Test
    void updateCar() {
    }

    @Test
    void deleteCar() {
    }
}