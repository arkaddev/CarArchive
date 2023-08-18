package com.example.CarArchive.repository;

import com.example.CarArchive.model.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartRepository extends JpaRepository<Part, Integer> {
}
