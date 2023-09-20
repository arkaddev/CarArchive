package com.example.CarArchive.service;

import com.example.CarArchive.dto.PartRequest;
import com.example.CarArchive.dto.PartResponse;
import com.example.CarArchive.mapper.PartMapper;
import com.example.CarArchive.model.Car;
import com.example.CarArchive.model.Part;
import com.example.CarArchive.model.User;
import com.example.CarArchive.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartServiceImpl implements PartService {

    private final PartRepository partRepository;
    private final PartMapper partMapper;

    @Autowired
    public PartServiceImpl(PartRepository partRepository, PartMapper partMapper) {
        this.partRepository = partRepository;
        this.partMapper = partMapper;
    }

    @Override
    public List<PartResponse> getAllParts() {
        return partRepository.findAll().stream().map(partMapper::partToPartResponse).toList();
    }

    @Override
    public PartResponse getPartById(Long id) {
        Optional<PartResponse> optionalPart = partRepository.findById(id).map(partMapper::partToPartResponse);
        PartResponse partResponse = optionalPart.orElseThrow();
        return partResponse;
    }

    @Override
    public PartResponse addNewPart(PartRequest partRequest) {

        Part part = partMapper.partRequestToPart(partRequest);
        partRepository.save(part);

        return new PartResponse(part.getId(), part.getName(), part.getMileage(), part.getDate(), part.getPartPrice(), part.getRepairPrice(), part.getNextExchange(), part.getCar().getId(), part.getCar().getUser().getId());
    }

    @Override
    public PartResponse updatePart(Long id, PartRequest partRequest) {

        Part partToUpdate = partRepository.findById(id).orElseThrow();

        partToUpdate.setName(partRequest.getName());
        partToUpdate.setMileage(partRequest.getMileage());
        partToUpdate.setDate(partRequest.getDate());
        partToUpdate.setPartPrice(partRequest.getPartPrice());
        partToUpdate.setRepairPrice(partRequest.getRepairPrice());
        partToUpdate.setNextExchange(partRequest.getNextExchange());
        Car car = new Car();
        car.setId(partRequest.getCarId());

        User user = new User();
        user.setId(partRequest.getUserId());

        car.setUser(user);

        partToUpdate.setCar(car);

        partRepository.save(partToUpdate);

        return new PartResponse(partToUpdate.getId(), partToUpdate.getName(), partToUpdate.getMileage(), partToUpdate.getDate(), partToUpdate.getPartPrice(), partToUpdate.getRepairPrice(), partToUpdate.getNextExchange(), partToUpdate.getCar().getId(), partToUpdate.getCar().getUser().getId());


    }

    @Override
    public void deletePart(Long id) {
        partRepository.deleteById(id);
    }

    @Override
    public List<Object[]> getPartsToExchangeByMileage(int km, Long carId) {
        return partRepository.findPartsByMileage(km, carId);
    }
}
