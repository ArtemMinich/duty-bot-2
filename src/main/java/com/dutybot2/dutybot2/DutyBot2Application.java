package com.dutybot2.dutybot2;

import com.dutybot2.dutybot2.bot.DutyBot;
import com.dutybot2.dutybot2.model.Cadet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class DutyBot2Application {

    public static void main(String[] args) {
        SpringApplication.run(DutyBot2Application.class, args);
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new DutyBot());
            System.out.println("Bot has been registered successfully!");
        } catch (TelegramApiException e) {
            e.printStackTrace();
            System.out.println("Error in bot registration");
        }
    }

}
