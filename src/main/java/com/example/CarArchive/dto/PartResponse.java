package com.example.CarArchive.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PartResponse {
    private Long id;
    private String name;
    private int mileage;
    private String date;
    private String partPrice;
    private String repairPrice;
    private int nextExchange;
    private Long carId;
    private Long userId;

    public PartResponse() {
    }

    public PartResponse(Long id, String name, int mileage, String date, String partPrice, String repairPrice, int nextExchange, Long carId, Long userId) {
        this.id = id;
        this.name = name;
        this.mileage = mileage;
        this.date = date;
        this.partPrice = partPrice;
        this.repairPrice = repairPrice;
        this.nextExchange = nextExchange;
        this.carId = carId;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPartPrice() {
        return partPrice;
    }

    public void setPartPrice(String partPrice) {
        this.partPrice = partPrice;
    }

    public String getRepairPrice() {
        return repairPrice;
    }

    public void setRepairPrice(String repairPrice) {
        this.repairPrice = repairPrice;
    }

    public int getNextExchange() {
        return nextExchange;
    }

    public void setNextExchange(int nextExchange) {
        this.nextExchange = nextExchange;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
