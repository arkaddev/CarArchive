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
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Car getCarById(Long id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        Car car = optionalCar.orElseThrow();
        return car;
    }

    @Override
    public CarResponse addNewCar(CarRequest carRequest) {
        Car car = carMapper.carRequestToCar(carRequest);
        CarResponse carResponse = carMapper.carToCarResponse(car);
        carRepository.save(car);
        return new CarResponse(car.getId(), car.getBrand(), car.getModel(), car.getOwner(), car.getParts());
    }

    @Override
    public Car updateCar(Long id, Car car) {
        Car carToUpdate = getCarById(id);
        carToUpdate.setBrand(car.getBrand());
        carToUpdate.setModel(car.getModel());
        carToUpdate.setOwner(car.getOwner());

        return carRepository.save(carToUpdate);
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}
