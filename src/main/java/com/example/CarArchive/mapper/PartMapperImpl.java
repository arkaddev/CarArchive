package com.example.CarArchive.mapper;

import com.example.CarArchive.dto.PartResponse;
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

        return partResponse;
    }
}
