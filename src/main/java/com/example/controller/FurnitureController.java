package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.service.FurnitureService;
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
    
    

}
