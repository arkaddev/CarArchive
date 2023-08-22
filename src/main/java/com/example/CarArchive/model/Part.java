package com.example.CarArchive.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "parts")
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int mileage;
    private String date;
    private String partPrice;
    private String repairPrice;
    private int nextExchange;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
}
