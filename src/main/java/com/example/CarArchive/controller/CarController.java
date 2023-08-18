package com.example.CarArchive.controller;

import com.example.CarArchive.model.Car;
import com.example.CarArchive.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    CarRepository carRepository;
    @Autowired
    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }



    @GetMapping("/all")
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }


}
