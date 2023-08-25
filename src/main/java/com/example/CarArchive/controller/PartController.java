package com.example.CarArchive.controller;

import com.example.CarArchive.model.Car;
import com.example.CarArchive.model.Part;
import com.example.CarArchive.repository.PartRepository;
import com.example.CarArchive.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/parts")
public class PartController {
    PartService partService;

    public PartController(PartService partService) {
        this.partService = partService;
    }

    @Autowired


    @GetMapping("/parts")
    public List<Part> getAllParts() {
        return partService.getAllParts();
    }

    @GetMapping("/parts/{id}")
    public Part getPartById(@PathVariable int id){
        return partService.getPartById(id);
    }
    @PostMapping("/parts")
    public Part addNewPart(@RequestBody Part part){
        return partService.addNewPart(part);
    }

    @PostMapping("/partsToExchange")
    public List<Object[]> getPartsToExchangeByMileage(@RequestParam int km, @RequestParam int carId ) {
        return partService.getPartsToExchangeByMileage(km, carId);
    }

}
