package com.dutybot2.dutybot2.dto;

import lombok.Data;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;


public record JwtRequest(String username, String password) {
}
