package com.dutybot2.dutybot2.controller;

import com.dutybot2.dutybot2.model.CadetUser;
import com.dutybot2.dutybot2.service.CadetUserService;
import com.dutybot2.dutybot2.util.CadetUserValidator;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private CadetUserService cadetUserService;
    private CadetUserValidator cadetUserValidator;

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute("cadetUser") @Valid CadetUser cadetUser,
                               BindingResult bindingResult){
        cadetUserValidator.validate(cadetUser,bindingResult);
        if(bindingResult.hasErrors()){
            return "auth/registration";
        }
        cadetUserService.save(cadetUser);
        return "redirect:/auth/login";
    }
    @GetMapping("/registration")
    public String getRegistration(Model model) {
        model.addAttribute("cadetUser", new CadetUser());
        return "auth/registration";
    }
    @GetMapping("/login")
    public String loginPage(){
        return "auth/login";
    }
}
