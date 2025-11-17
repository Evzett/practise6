package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.UserEntity;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByLogin(String login);

}
