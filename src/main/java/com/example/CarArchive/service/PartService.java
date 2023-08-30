package com.example.CarArchive.service;

import com.example.CarArchive.model.Part;

import java.util.List;

public interface PartService {
    List<Part> getAllParts();
    Part getPartById(int id);
    Part addNewPart(Part part);
    void deletePart(int id);


    List<Object[]> getPartsToExchangeByMileage(int km, int carId);


}
