package com.example.CarArchive.dto;

import com.example.CarArchive.model.Part;
import com.example.CarArchive.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CarResponse {
    private Long id;
    private String brand;
    private String model;
    private Long ownerId;
    private List<PartResponse> parts;

    public CarResponse() {
    }

    public CarResponse(Long id, String brand, String model, Long ownerId, List<PartResponse> parts) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.ownerId = ownerId;
        this.parts = parts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public List<PartResponse> getParts() {
        return parts;
    }

    public void setParts(List<PartResponse> parts) {
        this.parts = parts;
    }
}
