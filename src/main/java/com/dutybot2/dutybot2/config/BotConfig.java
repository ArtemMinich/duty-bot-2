package com.dutybot2.dutybot2.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
public class BotConfig {

    @Value("${dutybot2.name}")
    private String botName;

    @Value("${dutybot2.token}")
    private String botToken;
}
