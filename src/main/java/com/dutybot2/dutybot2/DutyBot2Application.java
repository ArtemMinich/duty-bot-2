package com.dutybot2.dutybot2;

import com.dutybot2.dutybot2.model.Cadet;
import com.dutybot2.dutybot2.repository.CadetRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DutyBot2Application {

    private static CadetRepository cadetRepository;
    public static void main(String[] args) {
        SpringApplication.run(DutyBot2Application.class, args);
    }

}
