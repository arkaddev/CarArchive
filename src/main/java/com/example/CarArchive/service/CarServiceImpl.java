package com.example.CarArchive.service;

import com.example.CarArchive.model.Car;
import com.example.CarArchive.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Car getCarById(int id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        Car car = optionalCar.orElseThrow();
        return car;
    }

    @Override
    public Car addNewCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car updateCar(int id, Car car) {
        Car carToUpdate = getCarById(id);
        carToUpdate.setBrand(car.getBrand());
        carToUpdate.setModel(car.getModel());
        carToUpdate.setOwner(car.getOwner());

        return carRepository.save(carToUpdate);
    }

    @Override
    public void deleteCar(int id) {
        carRepository.deleteById(id);
    }
}
