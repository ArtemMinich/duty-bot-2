package com.dutybot2.dutybot2.controller;

import com.dutybot2.dutybot2.model.CadetUser;
import com.dutybot2.dutybot2.repository.CadetUserRepository;
import com.dutybot2.dutybot2.util.Role;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
    private CadetUserRepository cadetUserRepository;
    @GetMapping
    public String getAdminPanel(Model model){
        model.addAttribute("users",cadetUserRepository.findAll());
        return "adminpanel";
    }

    @PutMapping("/setsergeant")
    public String setUserSergeant(@ModelAttribute("userId") Long userId){
        Optional<CadetUser> cadetUser =  cadetUserRepository.findById(userId);
        cadetUser.ifPresent(user -> user.setRole(Role.ROLE_SERGEANT));
        cadetUserRepository.save(cadetUser.get());
        return "redirect:/admin";
    }
}
