package com.dutybot2.dutybot2.service;

import com.dutybot2.dutybot2.model.CadetUser;
import com.dutybot2.dutybot2.repository.CadetUserRepository;
import com.dutybot2.dutybot2.util.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CadetUserService {
    private final CadetUserRepository cadetUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public CadetUser save(CadetUser cadetUser) {
        cadetUser.setPassword(passwordEncoder.encode(cadetUser.getPassword()));
        cadetUser.setRole(Role.ROLE_CADET);
        return cadetUserRepository.save(cadetUser);
    }
    public Optional<CadetUser> loadByUsername(String username){
        return cadetUserRepository.findByUsername(username);
    }
}
