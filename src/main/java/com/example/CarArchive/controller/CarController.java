package com.example.CarArchive.controller;

import com.example.CarArchive.model.Car;
import com.example.CarArchive.repository.CarRepository;
import com.example.CarArchive.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }


    @GetMapping("/all")
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }
}


