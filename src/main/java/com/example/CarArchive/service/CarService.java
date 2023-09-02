package com.example.CarArchive.service;

import com.example.CarArchive.dto.CarDTO;
import com.example.CarArchive.model.Car;

import java.util.List;

public interface CarService {
    List<Car> getAllCars();
    Car getCarById(Long id);
    Car addNewCar(CarDTO carDTO);
    Car updateCar(Long id, Car car);
    void deleteCar(Long id);

}
