package com.example.CarArchive.controller;

import com.example.CarArchive.model.Car;
import com.example.CarArchive.model.Part;
import com.example.CarArchive.repository.CarRepository;
import com.example.CarArchive.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/cars")
public class CarController {

    CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }


    @GetMapping("/cars")
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/car/{id}")
    public Car getCarById(@PathVariable int id) {
        return carService.getCarById(id);
    }

    @PostMapping("/cars")
    public Car addNewCar(@RequestBody Car car) {
        return carService.addNewCar(car);
    }

    @DeleteMapping("/car/{id}")
    public void deleteCar(@PathVariable int id){
        carService.deleteCar(id);
    }
}


