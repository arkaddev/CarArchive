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
    private User owner;
    private List<Part> parts;

    public CarResponse() {
    }

    public CarResponse(Long id, String brand, String model, User owner, List<Part> parts) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.owner = owner;
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }
}
