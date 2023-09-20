package com.example.CarArchive.service;

import com.example.CarArchive.dto.PartResponse;
import com.example.CarArchive.mapper.PartMapper;
import com.example.CarArchive.model.Part;
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
    public Part addNewPart(Part part) {
        return partRepository.save(part);
    }

    @Override
    public Part updatePart(Long id, Part part) {
//        Part partToUpdate = getPartById(id);
//
//        partToUpdate.setName(part.getName());
//        partToUpdate.setMileage(part.getMileage());
//        partToUpdate.setDate(part.getDate());
//        partToUpdate.setPartPrice(part.getPartPrice());
//        partToUpdate.setRepairPrice(part.getRepairPrice());
//        partToUpdate.setNextExchange(part.getNextExchange());
//
//        return partRepository.save(partToUpdate);
        return null;
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
