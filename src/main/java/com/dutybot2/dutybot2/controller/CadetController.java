package com.dutybot2.dutybot2.controller;

import com.dutybot2.dutybot2.service.CadetService;
import com.dutybot2.dutybot2.util.Caste;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cadets")
@AllArgsConstructor
public class CadetController {

    private CadetService cadetService;

    @GetMapping()
    public String getAllCadets(Model model){
        model.addAttribute("hobos",cadetService.findAllByCaste(Caste.HOBO));
        model.addAttribute("sluts",cadetService.findAllByCaste(Caste.SLUT));
        return "cadet/allcadets";
    }
}
