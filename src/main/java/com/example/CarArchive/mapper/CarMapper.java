package com.example.CarArchive.mapper;

import com.example.CarArchive.dto.CarRequest;
import com.example.CarArchive.dto.CarResponse;
import com.example.CarArchive.model.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface CarMapper {
    @Mapping(source = "user", target = "owner")
    CarRequest carToCarRequest(Car car);

    @Mapping(source = "owner", target = "user")
    Car carRequestToCar(CarRequest carRequest);
    @Mapping(source = "user", target = "owner")
    CarResponse carToCarResponse(Car car);
    @Mapping(source = "owner", target = "user")
    Car carResponseToCar(CarResponse carResponse);
}
