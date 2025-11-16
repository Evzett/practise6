package com.example.service;

import com.example.repository.FurnitureRepository;

import com.example.entity.Furniture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FurnitureService {

    @Autowired
    private FurnitureRepository repository;

    public void addFurniture(Furniture furniture) {
        repository.save(furniture);
    }

    public List<Furniture> getAllFurniture() {
        return repository.findAll();
    }

    public void deleteFurnitureById(Integer id) {
        repository.deleteById(id);
    }

    public void updateFurniture(Integer id, Furniture updated) {
        Furniture existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Не найдено"));
    
        existing.setName(updated.getName());
        existing.setType(updated.getType());
        existing.setPrice(updated.getPrice());
    
        repository.save(existing);
    }
    

    public Furniture getFurnitureById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public List<Furniture> searchByPrice(double minPrice) {
        return repository.findByPriceGreaterThan(minPrice);
    }

    public List<Furniture> searchByTypeAndPrice(String type, double minPrice){
        return repository.findByTypeAndPriceGreaterThan(type, minPrice);
    }

    public List<Furniture> searchByType(String type) {
        return repository.findByType(type);
    }
}
