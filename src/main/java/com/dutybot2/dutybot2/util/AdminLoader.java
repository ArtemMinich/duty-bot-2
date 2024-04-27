package com.dutybot2.dutybot2.util;

import com.dutybot2.dutybot2.model.CadetUser;
import com.dutybot2.dutybot2.repository.CadetUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AdminLoader implements CommandLineRunner {
    private final CadetUserRepository cadetUserRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {
        if(cadetUserRepository.findByUsername("temic").isEmpty()) {
            CadetUser user = new CadetUser();
            user.setUsername("temic");
            user.setPassword(passwordEncoder.encode("ztx505606"));
            user.setRole(Role.ROLE_ADMIN);
            cadetUserRepository.save(user);
        }
    }
}
