package com.dutybot2.dutybot2.controller;

import com.dutybot2.dutybot2.repository.CadetRepository;
import com.dutybot2.dutybot2.repository.DutyRepository;
import com.dutybot2.dutybot2.util.Caste;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sergeant")
@AllArgsConstructor
public class SergeantController {

    private CadetRepository cadetRepository;
    private DutyRepository dutyRepository;

    @GetMapping()
    public String getSergeantPanel(Model model){
        model.addAttribute("hobos",cadetRepository.findAllByCaste(Caste.HOBO));
        model.addAttribute("sluts",cadetRepository.findAllByCaste(Caste.SLUT));
        model.addAttribute("duties",dutyRepository.findAll());
        return "sergeant/sergeantpanel";
    }
}
