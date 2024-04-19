package com.dutybot2.dutybot2.repository;

import com.dutybot2.dutybot2.model.CadetUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CadetUserRepository extends JpaRepository<CadetUser, Long> {
    Optional<CadetUser> findByUsername(String username);
}