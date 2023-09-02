package com.example.CarArchive.service;

import com.example.CarArchive.model.Part;

import java.util.List;

public interface PartService {
    List<Part> getAllParts();
    Part getPartById(Long id);
    Part addNewPart(Part part);
    Part updatePart(Long id, Part part);
    void deletePart(Long id);


    List<Object[]> getPartsToExchangeByMileage(int km, Long carId);


}
