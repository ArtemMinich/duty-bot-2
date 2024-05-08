package com.dutybot2.dutybot2.service;

import com.dutybot2.dutybot2.details.CadetDetails;
import com.dutybot2.dutybot2.dto.RegistrationUserDto;
import com.dutybot2.dutybot2.model.CadetUser;
import com.dutybot2.dutybot2.repository.CadetUserRepository;
import com.dutybot2.dutybot2.util.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public CadetUser save(RegistrationUserDto registrationUserDto) {
        CadetUser cadetUser = new CadetUser();
        cadetUser.setUsername(registrationUserDto.username());
        cadetUser.setPassword(passwordEncoder.encode(registrationUserDto.password()));
        cadetUser.setRole(Role.ROLE_CADET);
        return cadetUserRepository.save(cadetUser);
    }
    public Optional<CadetUser> findByUsername(String username){
        return cadetUserRepository.findByUsername(username);
    }


}
