package com.example.CarArchive.service;

import com.example.CarArchive.dto.CarRequest;
import com.example.CarArchive.dto.CarResponse;
import com.example.CarArchive.exception.CarNotFoundException;
import com.example.CarArchive.exception.CarSaveException;
import com.example.CarArchive.mapper.CarMapper;
import com.example.CarArchive.model.Car;
import com.example.CarArchive.model.User;
import com.example.CarArchive.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    private final UserService userService;


    @Autowired
    public CarServiceImpl(CarRepository carRepository, CarMapper carMapper, UserService userService) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
        this.userService = userService;
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
        return optionalCar.orElseThrow(() -> new CarNotFoundException("Car does not exist"));
    }

    @Override
    public CarResponse addNewCar(CarRequest carRequest) {
        Car car = carMapper.carRequestToCar(carRequest);
        try {
            carRepository.save(car);
        } catch (Exception e) {
            throw new CarSaveException("Car cannot be saved");
        }
        return new CarResponse(car.getId(), car.getBrand(), car.getModel(), car.getUser(), car.getParts());
    }

    @Override
    public CarResponse updateCar(Long id, CarRequest carRequest) {
        Optional<Car> optionalCar = carRepository.findById(id);
        //if(optionalCar.isPresent()){
        Car carToUpdate = optionalCar.orElseThrow(() -> new CarNotFoundException("Car does not exist"));
        carToUpdate.setBrand(carRequest.getBrand());
        carToUpdate.setModel(carRequest.getModel());
        carToUpdate.setUser(carRequest.getOwner());

        try {
            carRepository.save(carToUpdate);
        } catch (Exception e) {
            throw new CarSaveException("Car cannot be saved");
        }

        return new CarResponse(carToUpdate.getId(), carToUpdate.getBrand(), carToUpdate.getModel(), carToUpdate.getUser(), carToUpdate.getParts());
        // }
//        else{
//          throw new CarNotFoundException("Car not exist");
//        }
    }


    @Override
    public String deleteCar(Long id) {
        try {
            carRepository.deleteById(id);
        } catch (Exception e) {
            throw new CarSaveException("Car cannot be removed");
        }

        return "Car " + id + " was deleted";
    }

    @Override
    public List<CarResponse> getCarsByLoggedUsername(String loggedUsername) {
        User user = userService.getUserByUsername(loggedUsername);
        Long userId = user.getId();

        return carRepository.findCarsByUserId(userId).stream()
                .map(carMapper::carToCarResponse)
                .toList();
    }
}


