package com.dutybot2.dutybot2.api;

import com.dutybot2.dutybot2.dto.JwtRequest;
import com.dutybot2.dutybot2.dto.RegistrationUserDto;
import com.dutybot2.dutybot2.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AuthRestController {
    private AuthService authService;
    @PostMapping("/auth")
    public ResponseEntity<?> getAuthToken(@RequestBody JwtRequest jwtRequest){
        return authService.createAuthToken(jwtRequest);
    }

    @PostMapping("/register")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) {
        return authService.createNewUser(registrationUserDto);
    }
}
