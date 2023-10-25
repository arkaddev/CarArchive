package com.example.CarArchive.controller;

import com.example.CarArchive.config.AuthenticationService;
import com.example.CarArchive.dto.PartRequest;
import com.example.CarArchive.dto.PartResponse;
import com.example.CarArchive.model.Car;
import com.example.CarArchive.model.Part;
import com.example.CarArchive.repository.PartRepository;
import com.example.CarArchive.service.PartService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/parts")
public class PartController {
    private final PartService partService;
    private final AuthenticationService authenticationService;

    @Autowired
    public PartController(PartService partService, AuthenticationService authenticationService) {
        this.partService = partService;
        this.authenticationService = authenticationService;
    }

    @Operation(summary = "get all parts", description = "")
    @GetMapping("/parts")
    public ResponseEntity<List<PartResponse>> getAllParts() {
        return ResponseEntity.ok(partService.getAllParts());
    }

    @Operation(summary = "get part by id", description = "")
    @GetMapping("/parts/{id}")
    public ResponseEntity<PartResponse> getPartById(@PathVariable Long id) {
        return ResponseEntity.ok(partService.getPartById(id));
    }

    @Operation(summary = "add new part", description = "")
    @PostMapping("/parts")
    public ResponseEntity<PartResponse> addNewPart(@RequestBody PartRequest partRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(partService.addNewPart(partRequest));
    }

    @Operation(summary = "delete part by id", description = "")
    @DeleteMapping("/parts/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(partService.deletePart(id));
    }

    @Operation(summary = "update part", description = "")
    @PutMapping("/parts/{id}")
    public ResponseEntity<PartResponse> updateCar(@PathVariable Long id, @RequestBody PartRequest partRequest) {
        return ResponseEntity.ok(partService.updatePart(id, partRequest));
    }

    @Operation(summary = "get parts to exchange by mileage", description = "")
    @PostMapping("/partsToExchange")
    public ResponseEntity<List<Object[]>> getPartsToExchangeByMileage(@RequestParam int km, @RequestParam Long carId) {
        return ResponseEntity.ok(partService.getPartsToExchangeByMileage(km, carId));
    }

    @Operation(summary = "get parts to exchange by mileage by logged user", description = "")
    @PostMapping("/u/partsToExchange")
    public ResponseEntity<List<Object[]>> getPartsToExchangeByMileageByLoggedUser(@RequestParam int km, @RequestParam Long carId) {
        return ResponseEntity.ok(partService.getPartsToExchangeByMileageByLoggedUsername(km, carId, getLoggedUser()));
    }

    @Operation(summary = "get all parts by logged user", description = "")
    @GetMapping("/u/parts")
    public ResponseEntity<List<PartResponse>> getAllPartsByLoggedUser() {
        return ResponseEntity.ok(partService.getAllPartsByLoggedUsername(getLoggedUser()));
    }

    @Operation(summary = "get part by id by logged user", description = "")
    @GetMapping("/u/parts/{id}")
    public ResponseEntity<PartResponse> getPartByIdByLoggedUser(@PathVariable Long id) {
        return ResponseEntity.ok(partService.getPartByIdByLoggedUsername(id, getLoggedUser()));
    }

    @Operation(summary = "add new part by id by logged user", description = "")
    @PostMapping("/u/parts")
    public ResponseEntity<PartResponse> addNewPartByLoggedUser(@RequestBody PartRequest partRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(partService.addNewPartByLoggedUsername(partRequest, getLoggedUser()));
    }

    @Operation(summary = "update part by id by logged user", description = "")
    @PutMapping("/u/parts/{id}")
    public ResponseEntity<PartResponse> updateCarByLoggedUser(@PathVariable Long id, @RequestBody PartRequest partRequest) {
        return ResponseEntity.ok(partService.updatePartByLoggedUsername(id, partRequest, getLoggedUser()));
    }

    @Operation(summary = "delete part by id by logged user", description = "")
    @DeleteMapping("/u/parts/{id}")
    public ResponseEntity<String> deleteCarByLoggedUser(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(partService.deletePartByLoggedUsername(id, getLoggedUser()));
    }
    public String getLoggedUser() {
        return authenticationService.getInfoAboutUser();
    }
}
