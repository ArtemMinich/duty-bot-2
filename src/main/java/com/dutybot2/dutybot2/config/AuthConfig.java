package com.dutybot2.dutybot2.config;

import com.dutybot2.dutybot2.service.CadetUserDetailsService;
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
    private final CadetUserDetailsService cadetDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(Customizer.withDefaults())
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/home","/error","/auth/registration","/auth/login").permitAll()
                        .requestMatchers("/sergeant/**","/bot/**").hasAnyRole("SERGEANT","ADMIN")
                        .anyRequest().hasAnyRole("SERGEANT","CADET","ADMIN")
                )
                .formLogin((form) -> form
                        .loginPage("/auth/login")
                        .permitAll()
                        .defaultSuccessUrl("/cadets")
                )
                .logout((logout) -> logout
                        .logoutSuccessUrl("/home")
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