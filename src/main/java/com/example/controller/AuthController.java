package com.example.controller;

import com.example.entity.UserEntity;
import com.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Страница логина (используем authPage)
    @GetMapping("/login")
    public String loginPage() {
        return "furniture/authPage";
    }

    // Страница регистрации (та же страница, но показываем форму регистрации)
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new UserEntity());
        return "furniture/authPage";
    }

    // Обработка регистрации
    @PostMapping("/register")
    public String register(
            @RequestParam String name,
            @RequestParam String login,
            @RequestParam String password
    ) {
        try {
            // Все новые пользователи регистрируются как USER
            userService.register(name, login, password);
            return "redirect:/login?registered=true";
        } catch (IllegalStateException e) {
            return "redirect:/register?error=" + e.getMessage();
        }
    }
}

