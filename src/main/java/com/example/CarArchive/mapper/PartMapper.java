package com.example.CarArchive.mapper;

import com.example.CarArchive.dto.PartResponse;
import com.example.CarArchive.model.Part;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PartMapper {
    @Mapping(source = "car.id", target = "carId")
    PartResponse partToPartResponse(Part part);

}
