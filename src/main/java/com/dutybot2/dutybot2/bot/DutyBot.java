package com.dutybot2.dutybot2.bot;

import com.dutybot2.dutybot2.config.BotConfig;
import com.dutybot2.dutybot2.model.Duty;
import com.dutybot2.dutybot2.service.CadetService;
import com.dutybot2.dutybot2.util.Caste;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.time.LocalDate;

@Component
@AllArgsConstructor
public class DutyBot extends TelegramLongPollingBot {

    private final BotConfig botConfig;
    private final CadetService cadetService;
    private final String GROUP_ID = "-1001693217220";


    @Override
    public String getBotToken() {
        return botConfig.getBotToken();
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public void onUpdateReceived(Update update) {

    }

    @Scheduled(cron = "0 0 18 * * ?")
    public void sendSchedulingMessage(){
        if(!botConfig.getExecuteDate().equals(LocalDate.now())){
            Duty duty = cadetService.creatDuty();
            cadetService.saveDuty(duty);
            sendTerka(duty);
            sendCubar(duty);
            sendToGroup(duty);
            botConfig.setExecuteDate(LocalDate.now().toString());
        }
    }

    public void sendTerka(Duty duty){
        sendMessage(duty.getTerkaCadet().getChatId(), getTextToCadetDuty(duty.getTerkaCadet().getCaste()));
    }
    public void sendCubar(Duty duty){
        sendMessage(duty.getCubarCadet().getChatId(), getTextToCadetDuty(duty.getCubarCadet().getCaste()));
    }

    public void sendToGroup(Duty duty){
        sendMessage(GROUP_ID,"Територія: " + duty.getTerkaCadet().getLastName()
                + "\nКубарь: " + duty.getCubarCadet().getLastName());
    }

    private String getTextToCadetDuty(Caste caste){
        return caste==Caste.HOBO?"Ти тєрка завтра":"Ти кубарь завтра";
    }

    private void sendMessage(String chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            System.out.println("Error in sendMessage");
        }
    }
}
