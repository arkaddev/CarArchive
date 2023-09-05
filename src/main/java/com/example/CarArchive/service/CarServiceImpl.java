package com.example.CarArchive.service;

import com.example.CarArchive.dto.CarRequest;
import com.example.CarArchive.dto.CarResponse;
import com.example.CarArchive.mapper.CarMapper;
import com.example.CarArchive.model.Car;
import com.example.CarArchive.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }


    @Override
    public List<CarResponse> getAllCars() {
        return carRepository.findAll().stream()
                .map(carMapper::carToCarResponse)
                .toList();
    }

    @Override
    public CarResponse getCarById(Long id) {
//        Optional<Car> optionalCar = carRepository.findById(id);
//        Car car = optionalCar.orElseThrow();
//        CarResponse carResponse = carMapper.carToCarResponse(car);
//        return carResponse;
        Optional<CarResponse> optionalCar = carRepository.findById(id).map(carMapper::carToCarResponse);
        return optionalCar.orElseThrow();
    }

    @Override
    public CarResponse addNewCar(CarRequest carRequest) {
        Car car = carMapper.carRequestToCar(carRequest);
        CarResponse carResponse = carMapper.carToCarResponse(car);
        carRepository.save(car);
        return new CarResponse(car.getId(), car.getBrand(), car.getModel(), car.getOwner(), car.getParts());
    }

    @Override
    public CarResponse updateCar(Long id, CarRequest carRequest) {
        Optional<Car> optionalCar = carRepository.findById(id);
        Car carToUpdate = optionalCar.get();
        carToUpdate.setBrand(carRequest.getBrand());
        carToUpdate.setModel(carRequest.getModel());
        carToUpdate.setOwner(carRequest.getOwner());

        carRepository.save(carToUpdate);
        return new CarResponse(carToUpdate.getId(), carToUpdate.getBrand(), carToUpdate.getModel(), carToUpdate.getOwner(), carToUpdate.getParts());
    }


    @Override
    public String deleteCar(Long id) {
        carRepository.deleteById(id);
        return "Car " + id + " was deleted";
    }
}
