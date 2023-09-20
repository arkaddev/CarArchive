package com.example.CarArchive.mapper;

import com.example.CarArchive.dto.PartRequest;
import com.example.CarArchive.dto.PartResponse;
import com.example.CarArchive.model.Car;
import com.example.CarArchive.model.Part;
import org.springframework.stereotype.Service;

@Service
public class PartMapperImpl implements PartMapper {
    @Override
    public PartResponse partToPartResponse(Part part) {

        PartResponse partResponse = new PartResponse();

        partResponse.setId(part.getId());
        partResponse.setName(part.getName());
        partResponse.setMileage(part.getMileage());
        partResponse.setDate(part.getDate());
        partResponse.setPartPrice(part.getPartPrice());
        partResponse.setRepairPrice(part.getRepairPrice());
        partResponse.setNextExchange(part.getNextExchange());
        partResponse.setCarId(part.getCar().getId());
        partResponse.setUserId(part.getCar().getUser().getId());

        return partResponse;
    }

    @Override
    public Part partRequestToPart(PartRequest partRequest) {


        Part part = new Part();

        part.setName(partRequest.getName());
        part.setMileage(partRequest.getMileage());
        part.setDate(partRequest.getDate());
        part.setPartPrice(partRequest.getPartPrice());
        part.setRepairPrice(partRequest.getRepairPrice());
        part.setNextExchange(partRequest.getNextExchange());

        Car car = new Car();
        car.setId(partRequest.getCarId());

        part.setCar(car);

        return part;
    }
}
