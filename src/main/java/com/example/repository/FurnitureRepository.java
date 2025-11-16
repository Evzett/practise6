package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Furniture;

import java.util.List;

public interface FurnitureRepository extends JpaRepository<Furniture, Integer> {

    List<Furniture> findByPriceGreaterThan(double price);
    

    List<Furniture> findByType(String type);
}
