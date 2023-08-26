package com.example.CarArchive.service;

import com.example.CarArchive.model.Car;

import java.util.List;

public interface CarService {
    List<Car> getAllCars();
    Car getCarById(int id);
}
