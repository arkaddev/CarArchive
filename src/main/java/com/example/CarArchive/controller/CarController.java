package com.example.CarArchive.controller;

import com.example.CarArchive.dto.CarDTO;
import com.example.CarArchive.model.Car;
import com.example.CarArchive.model.Part;
import com.example.CarArchive.repository.CarRepository;
import com.example.CarArchive.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "get all cars", description = "")
    @GetMapping("/cars")
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @Operation(summary = "get car by id", description = "")
    @GetMapping("/car/{id}")
    public Car getCarById(@PathVariable Long id) {
        return carService.getCarById(id);
    }

    @Operation(summary = "add new car", description = "")
    @PostMapping("/cars")
    public Car addNewCar(@RequestBody CarDTO carDTO) {
        return carService.addNewCar(carDTO);
    }

    @Operation(summary = "update car", description = "")
    @PutMapping("/cars/{id}")
    public Car updateCar(@PathVariable Long id, @RequestBody Car car) {
        return carService.updateCar(id, car);
    }

    @Operation(summary = "delete car by id", description = "")
    @DeleteMapping("/car/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }
}


