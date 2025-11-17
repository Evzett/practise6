package com.example.config;

import com.example.entity.UserEntity;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    @Override
    public void run(String... args) {
        // проверяем, есть ли админ
        if (repo.findByLogin("admin").isEmpty()) {

            UserEntity admin = new UserEntity();
            admin.setName("Админ");
            admin.setLogin("admin");
            admin.setPassword(encoder.encode("admin")); // пароль admin
            admin.setRole("ADMIN");

            repo.save(admin);
            System.out.println("Админ создан автоматически");
        } else {
            System.out.println("Админ уже существует");
        }
    }
}
