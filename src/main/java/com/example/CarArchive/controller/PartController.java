package com.example.CarArchive.controller;

import com.example.CarArchive.model.Car;
import com.example.CarArchive.model.Part;
import com.example.CarArchive.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parts")
public class PartController {
    PartRepository partRepository;

    @Autowired
    public PartController(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    @GetMapping("/all")
    public List<Part> getAllParts() {
        return partRepository.findAll();
    }

    @PostMapping("/info")
    public List<Part> getPartsToExchangeByMileage(@RequestBody Car car) {
        return null;
    }

}
