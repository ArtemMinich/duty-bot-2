package com.dutybot2.dutybot2.config;

import com.dutybot2.dutybot2.service.CadetDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class AuthConfig {
    private final CadetDetailsService cadetDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(Customizer.withDefaults())
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/error","/registration").permitAll()
                        .requestMatchers("/cadet").hasRole("CADET")
                        .requestMatchers("/sergeant").hasRole("SERGEANT")
                        .anyRequest().hasAnyRole("SERGEANT","CADET","ADMIN")
                )
                .formLogin((form) -> form
                        .defaultSuccessUrl("/cadet")
                )
                .logout((logout) -> logout
                        .logoutSuccessUrl("/auth/home")
                )
                .build();
    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(getPasswordEncoder());
        daoAuthenticationProvider.setUserDetailsService(cadetDetailsService);
        return daoAuthenticationProvider;
    }
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}