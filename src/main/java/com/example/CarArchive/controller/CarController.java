package com.example.CarArchive.controller;

import com.example.CarArchive.config.AuthenticationService;
import com.example.CarArchive.config.CustomUserDetailsService;
import com.example.CarArchive.dto.CarRequest;
import com.example.CarArchive.dto.CarResponse;
import com.example.CarArchive.model.Car;
import com.example.CarArchive.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/cars")
public class CarController {

    private final CarService carService;
    private final AuthenticationService authenticationService;

    @Autowired
    public CarController(CarService carService, AuthenticationService authenticationService) {
        this.carService = carService;
        this.authenticationService = authenticationService;
    }

    @Operation(summary = "get all cars", description = "")
    @GetMapping("/cars")
    public ResponseEntity<List<CarResponse>> getAllCars() {
        return ResponseEntity.ok(carService.getAllCars());
    }

    @Operation(summary = "get car by id", description = "")
    @GetMapping("/cars/{id}")
    public ResponseEntity<CarResponse> getCarById(@PathVariable Long id) {
        return ResponseEntity.ok(carService.getCarById(id));
    }

    @Operation(summary = "add new car", description = "")
    @PostMapping("/cars")
    public ResponseEntity<CarResponse> addNewCar(@RequestBody CarRequest carRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carService.addNewCar(carRequest));
    }

    @Operation(summary = "update car", description = "")
    @PutMapping("/cars/{id}")
    public ResponseEntity<CarResponse> updateCar(@PathVariable Long id, @RequestBody CarRequest carRequest) {
        return ResponseEntity.ok(carService.updateCar(id, carRequest));
    }

    @Operation(summary = "delete car by id", description = "")
    @DeleteMapping("/cars/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(carService.deleteCar(id));
    }

    @Operation(summary = "get cars by logged user", description = "")
    @GetMapping("/cars/user/")
    public ResponseEntity<List<CarResponse>> getCarsByLoggedUser() {
        return ResponseEntity.ok(carService.getAllCarsByLoggedUsername(getLoggedUser()));
    }

    @Operation(summary = "get car by id by logged user", description = "")
    @GetMapping("/cars/user/{id}")
    public ResponseEntity<CarResponse> getCarByIdByLoggedUser(@PathVariable Long id) {
        return ResponseEntity.ok(carService.getCarByIdByLoggedUsername(id, getLoggedUser()));
    }

    public String getLoggedUser() {
        return authenticationService.getInfoAboutUser();
    }
}


