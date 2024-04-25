package com.dutybot2.dutybot2.util;

import com.dutybot2.dutybot2.model.CadetUser;
import com.dutybot2.dutybot2.repository.CadetUserRepository;
import com.dutybot2.dutybot2.service.CadetUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@AllArgsConstructor
public class CadetUserValidator implements Validator {
    private CadetUserService cadetUserService;

    @Override
    public boolean supports(Class<?> clazz) {
        return CadetUser.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CadetUser cadetUser = (CadetUser) target;
        if(cadetUserService.loadByUsername(cadetUser.getUsername()).isPresent()){
            errors.rejectValue("username","","Такий логін вже зареєстрований");
        }
    }
}
