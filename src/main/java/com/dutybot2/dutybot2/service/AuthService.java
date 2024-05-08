package com.dutybot2.dutybot2.service;

import com.dutybot2.dutybot2.details.CadetDetails;
import com.dutybot2.dutybot2.dto.JwtRequest;
import com.dutybot2.dutybot2.dto.JwtResponse;
import com.dutybot2.dutybot2.dto.RegistrationUserDto;
import com.dutybot2.dutybot2.exception.AppError;
import com.dutybot2.dutybot2.model.CadetUser;
import com.dutybot2.dutybot2.util.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@AllArgsConstructor
public class AuthService {
    private JwtUtils jwtUtils;
    private AuthenticationManager authenticationManager;
    private CadetUserService cadetUserService;
    private  CadetUserDetailsService cadetUserDetailsService;

    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest jwtRequest){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.username(),jwtRequest.password()));
        } catch (BadCredentialsException e){
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Неправльний логин або пароль"), HttpStatus.UNAUTHORIZED);
        }
        CadetDetails cadetDetails = cadetUserDetailsService.loadUserByUsername(jwtRequest.username());
        String jwtToken = jwtUtils.genereteToken(cadetDetails);
        return new ResponseEntity<>(new JwtResponse(jwtToken), HttpStatus.CREATED);
    }

    public ResponseEntity<?> createNewUser(RegistrationUserDto registrationUserDto) {
        if(cadetUserService.findByUsername(registrationUserDto.username()).isPresent()){
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Користувач з таким ім'ям вже існує"), HttpStatus.BAD_REQUEST);
        }
        CadetUser cadetUser = cadetUserService.save(registrationUserDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(cadetUser);
    }
}
