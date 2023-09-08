package com.example.CarArchive.repository;

import com.example.CarArchive.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    //@Query("SELECT p FROM Cars p WHERE p.user_id = :userId")
    List<Car> findCarsByUserId(Long userId);
}
