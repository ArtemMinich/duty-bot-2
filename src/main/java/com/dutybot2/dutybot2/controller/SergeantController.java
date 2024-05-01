package com.dutybot2.dutybot2.controller;

import com.dutybot2.dutybot2.model.Cadet;
import com.dutybot2.dutybot2.service.CadetService;
import com.dutybot2.dutybot2.service.DutyService;
import com.dutybot2.dutybot2.util.CadetStatus;
import com.dutybot2.dutybot2.util.Caste;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sergeant")
@AllArgsConstructor
public class SergeantController {

    private CadetService cadetService;
    private DutyService dutyService;

    @GetMapping("/cadets")
    public String getSergeantPanel(Model model) {
        model.addAttribute("hobos", cadetService.findAllByCaste(Caste.HOBO));
        model.addAttribute("sluts", cadetService.findAllByCaste(Caste.SLUT));
        model.addAttribute("duties", dutyService.findAll());
        return "sergeant/sergeantpanel";
    }

    @GetMapping("/cadets/{id}")
    public String getCadetById(@PathVariable("id") int id, Model model) {
        model.addAttribute("cadet", cadetService.getReferenceById(id));
        return "cadet/cadet";
    }

    @PutMapping("/cadets/{id}/addduty")
    public String addDutyToCadetById(@PathVariable("id") int id) {
        Cadet cadet = cadetService.getReferenceById(id);
        cadet.incrementDutyDayCount();
        cadetService.save(cadet);
        return "redirect:/sergeant/cadets/{id}";
    }
    @PutMapping("/cadets/{id}/removeduty")
    public String removeDutyToCadetById(@PathVariable("id") int id) {
        Cadet cadet = cadetService.getReferenceById(id);
        cadet.decrementDutyDayCount();
        cadetService.save(cadet);
        return "redirect:/sergeant/cadets/{id}";
    }

    @PutMapping("/cadets/{id}/setstatus")
    public String setStatusCadetById(@PathVariable("id") int id) {
        Cadet cadet = cadetService.getReferenceById(id);
        if(cadet.isFree()){
            cadet.setStatus(CadetStatus.BUSY);
        } else {
            cadet.setStatus(CadetStatus.FREE);
        }
        cadetService.save(cadet);
        return "redirect:/sergeant/cadets/{id}";
    }
}
