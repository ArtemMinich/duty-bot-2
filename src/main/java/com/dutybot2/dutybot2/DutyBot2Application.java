package com.dutybot2.dutybot2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DutyBot2Application {

    public static void main(String[] args) {
        SpringApplication.run(DutyBot2Application.class, args);
    }

}
