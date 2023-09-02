package com.example.CarArchive.service;

import com.example.CarArchive.model.Part;
import com.example.CarArchive.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartServiceImpl implements PartService {

    PartRepository partRepository;

    @Autowired
    public PartServiceImpl(PartRepository partRepository) {
        this.partRepository = partRepository;
    }


    @Override
    public List<Part> getAllParts() {
        return partRepository.findAll();
    }

    @Override
    public Part getPartById(Long id) {
        Optional<Part> optionalPart = partRepository.findById(id);
        Part part = optionalPart.orElseThrow();
        return part;
    }

    @Override
    public Part addNewPart(Part part) {
        return partRepository.save(part);
    }

    @Override
    public Part updatePart(Long id, Part part) {
        Part partToUpdate = getPartById(id);

        partToUpdate.setName(part.getName());
        partToUpdate.setMileage(part.getMileage());
        partToUpdate.setDate(part.getDate());
        partToUpdate.setPartPrice(part.getPartPrice());
        partToUpdate.setRepairPrice(part.getRepairPrice());
        partToUpdate.setNextExchange(part.getNextExchange());

        return partRepository.save(partToUpdate);
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
