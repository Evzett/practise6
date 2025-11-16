package com.example.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.entity.Furniture;
import com.example.service.FurnitureService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class FurnitureController {
    private final FurnitureService service;

    public FurnitureController(FurnitureService service){
        this.service = service;
    }
    
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/furniture")
    public String ShowList( Model model){
        model.addAttribute("furnitureList", service.getAllFurniture());
        return "furniture/list";
    }

    @GetMapping("/furniture/new")
    public String showAddFurniture(Model model) {
        model.addAttribute("furniture", new Furniture());
        return "furniture/new";
    }
    
    @PostMapping("/furniture/new")
    public String addFurniture(
            @Valid @ModelAttribute("furniture") Furniture furniture,
            BindingResult errors
    ) {
        if (errors.hasErrors()) {
            return "furniture/new";
        }
    
        service.addFurniture(furniture);
        return "redirect:/furniture";
    }

    @GetMapping("/furniture/{id}/edit")
    public String editFurniture(@PathVariable Integer id, Model model) {
        Furniture furniture = service.getFurnitureById(id);
        model.addAttribute("furniture", furniture);
        return "furniture/edit";
    }
    

    @PostMapping("/furniture/{id}/edit")
    public String updateFurniture(
        @PathVariable Integer id,
        @Valid @ModelAttribute("furniture") Furniture furniture,
        BindingResult bindingResult
) {
    if (bindingResult.hasErrors()) {
        return "furniture/edit";
    }

    service.updateFurniture(id, furniture);
    return "redirect:/furniture";
}

    @GetMapping("/furniture/{id}/delete")
    public String deleteFurniture(@PathVariable Integer id) {
        service.deleteFurnitureById(id);
        return "redirect:/furniture";
    }


    @GetMapping("/furniture/search")
    public String search(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Double price,
            Model model
    ) {
        List<Furniture> results;
    
        boolean noType = (type == null || type.isBlank());
        boolean noPrice = (price == null);
    
        if (noType && noPrice) {
            return "furniture/search";
        }
    
        if (noType && !noPrice) {
            results = service.searchByPrice(price);
        }

        else if (!noType && noPrice) {
            results = service.searchByType(type);
        }
    
        else {
            results = service.searchByTypeAndPrice(type, price);
        }
    
        model.addAttribute("results", results);
        return "furniture/search";
    }
    

    
}
    
    
    
    
