package com.example.CarArchive.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PartRequest{
    private String name;
    private int mileage;
    private String date;
    private String partPrice;
    private String repairPrice;
    private int nextExchange;
    private Long carId;
    private Long userId;

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
