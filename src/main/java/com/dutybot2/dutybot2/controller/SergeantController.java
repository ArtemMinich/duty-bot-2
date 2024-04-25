package com.dutybot2.dutybot2.controller;

import com.dutybot2.dutybot2.model.Cadet;
import com.dutybot2.dutybot2.repository.CadetRepository;
import com.dutybot2.dutybot2.repository.DutyRepository;
import com.dutybot2.dutybot2.util.Caste;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sergeant")
@AllArgsConstructor
public class SergeantController {

    private CadetRepository cadetRepository;
    private DutyRepository dutyRepository;

    @GetMapping("/cadets")
    public String getSergeantPanel(Model model) {
        model.addAttribute("hobos", cadetRepository.findAllByCaste(Caste.HOBO));
        model.addAttribute("sluts", cadetRepository.findAllByCaste(Caste.SLUT));
        model.addAttribute("duties", dutyRepository.findAll());
        return "sergeant/sergeantpanel";
    }

    @GetMapping("/cadets/{id}")
    public String getCadetById(@PathVariable("id") int id, Model model) {
        model.addAttribute("cadet", cadetRepository.findById(id).get());
        return "cadet/cadet";
    }

    @PutMapping("/cadets/{id}/addduty")
    public String addDutyToCadetById(@PathVariable("id") int id) {
        Cadet cadet = cadetRepository.findById(id).get();
        cadet.setDutyDayCount(cadet.getDutyDayCount() + 1 );
        cadetRepository.save(cadet);
        return "redirect:/sergeant/cadets";
    }
    @PutMapping("/cadets/{id}/removeduty")
    public String removeDutyToCadetById(@PathVariable("id") int id) {
        Cadet cadet = cadetRepository.findById(id).get();
        cadet.setDutyDayCount(Math.max(cadet.getDutyDayCount() - 1, 0));
        cadetRepository.save(cadet);
        return "redirect:/sergeant/cadets";
    }
}
