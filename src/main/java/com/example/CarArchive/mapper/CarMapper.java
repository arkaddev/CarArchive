package com.example.CarArchive.mapper;

import com.example.CarArchive.dto.CarRequest;
import com.example.CarArchive.dto.CarResponse;
import com.example.CarArchive.model.Car;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CarMapper {
    CarRequest carToCarRequest(Car car);
    Car carRequestToCar(CarRequest carRequest);

    CarResponse carToCarResponse(Car car);
    Car carResponseToCar(CarResponse carResponse);
}
