package com.dutybot2.dutybot2.service;

import com.dutybot2.dutybot2.details.CadetDetails;
import com.dutybot2.dutybot2.model.CadetUser;
import com.dutybot2.dutybot2.repository.CadetUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CadetUserDetailsService implements UserDetailsService {
    private CadetUserRepository cadetUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<CadetUser> cadetUser = cadetUserRepository.findByUsername(username);
        if(username.isEmpty()){
            throw  new UsernameNotFoundException("Користувача не знайдено!!!");
        }

        return new CadetDetails(cadetUser.get());
    }
}
