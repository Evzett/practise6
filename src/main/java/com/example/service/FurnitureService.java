package com.example.service;

import model.Furniture;
import com.example.repository.FurnitureRepository;
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

    public void updateFurniture(Integer id, String name, String type, double price) {
        Optional<Furniture> furnitureOptional = repository.findById(id);
        if (furnitureOptional.isPresent()) {
            Furniture furniture = furnitureOptional.get();
            furniture.setName(name);
            furniture.setType(type);
            furniture.setPrice(price);
            repository.save(furniture);
        } else {
            System.out.println("Запись с таким ID не найдена.");
        }
    }

    public List<Furniture> searchByPrice(double minPrice) {
        return repository.findByPriceGreaterThan(minPrice);
    }

    public List<Furniture> searchByType(String type) {
        return repository.findByType(type);
    }
}
