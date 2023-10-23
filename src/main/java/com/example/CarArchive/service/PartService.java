package com.example.CarArchive.service;

import com.example.CarArchive.dto.PartRequest;
import com.example.CarArchive.dto.PartResponse;
import com.example.CarArchive.model.Part;

import java.util.List;

public interface PartService {
    List<PartResponse> getAllParts();
    PartResponse getPartById(Long id);
    PartResponse addNewPart(PartRequest partRequest);
    PartResponse updatePart(Long id, PartRequest partRequest);
    String deletePart(Long id);

    List<Object[]> getPartsToExchangeByMileage(int km, Long carId);
    List<PartResponse> getPartsToExchangeByMileageByLoggedUsername(int km, Long carId, String loggedUsername);

    List<PartResponse> getAllPartsByLoggedUsername(String loggedUsername);
    PartResponse getPartByIdByLoggedUsername(Long id, String loggedUsername);
    PartResponse addNewPartByLoggedUsername(PartRequest partRequest, String loggedUsername);
    PartResponse updatePartByLoggedUsername(Long id, PartRequest partRequest, String loggedUsername);
    String deletePartByLoggedUsername(Long id, String loggedUsername);
}
