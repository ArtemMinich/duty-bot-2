package com.dutybot2.dutybot2;

import com.dutybot2.dutybot2.repository.CadetRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;


@SpringBootApplication
@EnableScheduling
public class DutyBot2Application {

    private static CadetRepository cadetRepository;
    public static void main(String[] args) {
        SpringApplication.run(DutyBot2Application.class, args);
    }

}
