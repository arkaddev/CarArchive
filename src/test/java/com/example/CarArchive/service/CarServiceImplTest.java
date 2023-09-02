package com.example.CarArchive.service;

import com.example.CarArchive.model.Car;
import com.example.CarArchive.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class CarServiceImplTest {

    @Autowired
    private CarService carService;

    @MockBean
    private CarRepository carRepository;

    @Test
    void getAllCars() {
        // przygotowanie mocka, repozytorium
        Car car = new Car();
        car.setBrand("testBrand");
        car.setModel("testModel");
        car.setOwner("testOwner");

        Car car2 = new Car();
        car2.setBrand("testBrand2");
        car2.setModel("testModel2");
        car2.setOwner("testOwner2");

        List<Car> cars = Arrays.asList(car, car2);

        when(carRepository.findAll()).thenReturn(cars);
        // pobranie samochodow za pomoca serwisu
        List<Car> serviceCars = carService.getAllCars();

        assertThat(serviceCars).isNotEmpty();
        assertThat(serviceCars).hasSize(2);
    }

    @Test
    void getCarById() {
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