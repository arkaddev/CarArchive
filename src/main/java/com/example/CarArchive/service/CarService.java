package com.example.CarArchive.service;

import com.example.CarArchive.dto.CarRequest;
import com.example.CarArchive.dto.CarResponse;
import com.example.CarArchive.model.Car;

import java.util.List;

public interface CarService {
    List<CarResponse> getAllCars();
    CarResponse getCarById(Long id);
    CarResponse addNewCar(CarRequest carRequest);
    CarResponse updateCar(Long id, CarRequest carRequest);
    String deleteCar(Long id);

    List<Car> getAllCarbByLoggedUsername(String loggedUsername);

}
