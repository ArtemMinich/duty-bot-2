package com.dutybot2.dutybot2.controller;

import com.dutybot2.dutybot2.bot.DutyBot;
import com.dutybot2.dutybot2.config.BotConfig;
import com.dutybot2.dutybot2.model.Duty;
import com.dutybot2.dutybot2.service.CadetService;
import com.dutybot2.dutybot2.service.DutyService;
import com.dutybot2.dutybot2.util.Caste;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/bot")
@AllArgsConstructor
public class BotController {
    private CadetService cadetService;
    private DutyBot bot;
    private BotConfig botConfig;
    private DutyService dutyService;

    @GetMapping("/createduty")
    public String createDuty(Model model){
        model.addAttribute("duty",dutyService.creatDuty());
        model.addAttribute("hobos",cadetService.findAllByCaste(Caste.HOBO));
        model.addAttribute("sluts",cadetService.findAllByCaste(Caste.SLUT));
        return "sergeant/duty";
    }
    @PostMapping("/saveduty")
    public String saveDuty(@ModelAttribute Duty duty,
                           @RequestParam("cubar") Integer cubarCadteID,
                           @RequestParam("terka") Integer terkaCadteID){
        duty.setCubarCadet(cadetService.getReferenceById(cubarCadteID));
        duty.setTerkaCadet(cadetService.getReferenceById(terkaCadteID));
        dutyService.save(duty);
        bot.sendCubar(duty);
        bot.sendTerka(duty);
        bot.sendToGroup(duty) ;
        botConfig.setExecuteDate(LocalDate.now().toString());
        return "redirect:/sergeant/cadets";
    }
}
