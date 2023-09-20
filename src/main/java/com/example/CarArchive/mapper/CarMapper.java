package com.example.CarArchive.mapper;

import com.example.CarArchive.dto.CarRequest;
import com.example.CarArchive.dto.CarResponse;
import com.example.CarArchive.model.Car;




public interface CarMapper {
    //@Mapping(source = "ownerId", target = "user.id")
    Car carRequestToCar(CarRequest carRequest);
    //@Mapping(source = "user.id", target = "ownerId")
    CarResponse carToCarResponse(Car car);
}
