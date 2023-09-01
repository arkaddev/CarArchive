package com.example.CarArchive.controller;

import com.example.CarArchive.model.Car;
import com.example.CarArchive.model.Part;
import com.example.CarArchive.repository.PartRepository;
import com.example.CarArchive.service.PartService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/parts")
public class PartController {
    PartService partService;

    @Autowired
    public PartController(PartService partService) {
        this.partService = partService;
    }


    @Operation(summary = "get all parts", description = "")
    @GetMapping("/parts")
    public List<Part> getAllParts() {
        return partService.getAllParts();
    }

    @Operation(summary = "get part by id", description = "")
    @GetMapping("/parts/{id}")
    public Part getPartById(@PathVariable int id) {
        return partService.getPartById(id);
    }

    @Operation(summary = "add new part", description = "")
    @PostMapping("/parts")
    public Part addNewPart(@RequestBody Part part) {
        return partService.addNewPart(part);
    }

    @Operation(summary = "delete part by id", description = "")
    @DeleteMapping("/part/{id}")
    public void deleteCar(@PathVariable int id){
        partService.deletePart(id);
    }

    @Operation(summary = "update part", description = "")
    @PutMapping("/parts/{id}")
    public Part updateCar(@PathVariable int id, @RequestBody Part part) {
        return partService.updatePart(id, part);
    }

    @Operation(summary = "get parts to exchange by mileage", description = "")
    @PostMapping("/partsToExchange")
    public List<Object[]> getPartsToExchangeByMileage(@RequestParam int km, @RequestParam int carId) {
        return partService.getPartsToExchangeByMileage(km, carId);
    }

}
