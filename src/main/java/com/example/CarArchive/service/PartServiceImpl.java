package com.example.CarArchive.service;

import com.example.CarArchive.dto.PartRequest;
import com.example.CarArchive.dto.PartResponse;
import com.example.CarArchive.exception.PartDeleteException;
import com.example.CarArchive.exception.PartNotFoundException;
import com.example.CarArchive.exception.PartSaveException;
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
        PartResponse partResponse = optionalPart.orElseThrow(() -> new PartNotFoundException("Part does not exist"));
        return partResponse;
    }

    @Override
    public PartResponse addNewPart(PartRequest partRequest) {

        Part part = partMapper.partRequestToPart(partRequest);

        try {
            partRepository.save(part);
        } catch (Exception e) {
            throw new PartSaveException("Part cannot be saved");
        }

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

        try {
            partRepository.save(partToUpdate);
        } catch (Exception e) {
            throw new PartSaveException("Part cannot be updated");
        }

        return new PartResponse(partToUpdate.getId(), partToUpdate.getName(), partToUpdate.getMileage(), partToUpdate.getDate(), partToUpdate.getPartPrice(), partToUpdate.getRepairPrice(), partToUpdate.getNextExchange(), partToUpdate.getCar().getId(), partToUpdate.getCar().getUser().getId());
    }

    @Override
    public String deletePart(Long id) {

        Part optionalPart = partRepository.findById(id).orElseThrow(() -> new PartNotFoundException("Part does not exist"));

        try {
            partRepository.deleteById(id);
        } catch (Exception e) {
            throw new PartDeleteException("Part cannot be deleted");
        }

        return "Part " + id + " was deleted";
    }

    @Override
    public List<Object[]> getPartsToExchangeByMileage(int km, Long carId) {
        return partRepository.findPartsByMileage(km, carId);
    }
}
