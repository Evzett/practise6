package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.service.UserService;

@Controller
public class AuthController {

    private final UserService service;
    
    public AuthController (UserService service) {
        this.service = service;};

    @GetMapping("/login")
    public String showLoginPage(){
        return "furniture/authPage";
    }    
}
