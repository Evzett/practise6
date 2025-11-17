package com.example.service;

import com.example.entity.UserEntity;
import com.example.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository repository;

    private final PasswordEncoder encoder;
    
    public UserService(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserEntity u = repository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User
                .withUsername(u.getLogin())
                .password(u.getPassword())
                .roles(u.getRole())
                .build();
    }

    public void register(String name, String login, String password){
        if (repository.findByLogin(login).isPresent()){
            throw new IllegalStateException("Такой пользователь уже существует");
        }
        UserEntity u = new UserEntity();
        u.setName(name);
        u.setLogin(login);
        u.setPassword(encoder.encode(password));
        u.setRole("USER");
        repository.save(u);
    }
}
