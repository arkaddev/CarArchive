package com.example.CarArchive.mapper;

import com.example.CarArchive.dto.CarRequest;
import com.example.CarArchive.dto.CarResponse;
import com.example.CarArchive.model.Car;
import com.example.CarArchive.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarMapperImpl implements CarMapper {

    private final PartMapper partMapper;

    @Autowired
    public CarMapperImpl(PartMapper partMapper) {
        this.partMapper = partMapper;
    }


    @Override
    public CarResponse carToCarResponse(Car car) {
        CarResponse carResponse = new CarResponse();

        carResponse.setId(car.getId());
        carResponse.setBrand(car.getBrand());
        carResponse.setModel(car.getModel());
        carResponse.setOwnerId(car.getUser().getId());
        carResponse.setParts(car.getParts().stream().map(partMapper::partToPartResponse).toList());

        return carResponse;
    }

    @Override
    public Car carRequestToCar(CarRequest carRequest) {

        Car car = new Car();

        car.setBrand(carRequest.getBrand());
        car.setModel(carRequest.getModel());
        User user = new User();
        user.setId(carRequest.getOwnerId());
        car.setUser(user);

        return car;
    }
}
