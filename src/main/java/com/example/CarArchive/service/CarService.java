package com.example.CarArchive.service;

import com.example.CarArchive.dto.CarRequest;
import com.example.CarArchive.model.Car;

import java.util.List;

public interface CarService {
    List<Car> getAllCars();
    Car getCarById(Long id);
    Car addNewCar(CarRequest carRequest);
    Car updateCar(Long id, Car car);
    void deleteCar(Long id);

}
