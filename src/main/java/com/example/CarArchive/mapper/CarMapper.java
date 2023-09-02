package com.example.CarArchive.mapper;

import com.example.CarArchive.dto.CarDTO;
import com.example.CarArchive.model.Car;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CarMapper {
    CarDTO carToCarDTO(Car car);
    Car carDTOtoCar(CarDTO carDTO);
}
