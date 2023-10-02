package com.example.CarArchive.repository;

import com.example.CarArchive.model.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {
    @Query("SELECT p.name, (p.mileage + p.nextExchange - :inputMileage) AS total FROM Part p WHERE p.nextExchange > 0 AND p.car.id = :inputCarId ORDER BY total ASC")
    List<Object[]> findPartsByMileage(int inputMileage, Long inputCarId);
    List<Part> findPartsByCarId(Long userId);
}
