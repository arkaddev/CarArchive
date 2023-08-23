package com.example.CarArchive.controller;

import com.example.CarArchive.model.Car;
import com.example.CarArchive.model.Part;
import com.example.CarArchive.repository.PartRepository;
import com.example.CarArchive.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parts")
public class PartController {
    PartService partService;

    public PartController(PartService partService) {
        this.partService = partService;
    }

    @Autowired


    @GetMapping("/all")
    public List<Part> getAllParts() {
        return partService.getAllParts();
    }

    @PostMapping("/info")
    public List<Part> getPartsToExchangeByMileage(@RequestBody Car car) {
        return null;
    }

}
