package com.dutybot2.dutybot2.util;

import com.dutybot2.dutybot2.model.Cadet;
import com.dutybot2.dutybot2.model.CadetUser;
import com.dutybot2.dutybot2.repository.CadetRepository;
import com.dutybot2.dutybot2.repository.CadetUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final CadetUserRepository cadetUserRepository;
    private final CadetRepository cadetRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {
        if(cadetUserRepository.findByUsername("temic").isEmpty()) {
            CadetUser user = new CadetUser();
            user.setUsername("temic");
            user.setPassword(passwordEncoder.encode("ztx505606"));
            user.setRole(Role.ROLE_ADMIN);
            cadetUserRepository.save(user);
        }
        Cadet cadet1 = new Cadet();
        cadet1.setId(1);
        cadet1.setCaste(Caste.SLUT);
        cadet1.setChatId("651512441");
        cadet1.setDutyDayCount(0);
        cadet1.setLastName("Босненко");
        cadet1.setStatus(CadetStatus.FREE);
        cadetRepository.save(cadet1);
        //---
        Cadet cadet2 = new Cadet();
        cadet2.setId(2);
        cadet2.setCaste(Caste.SLUT);
        cadet2.setChatId("745851478");
        cadet2.setDutyDayCount(0);
        cadet2.setLastName("Віщун");
        cadet2.setStatus(CadetStatus.FREE);
        cadetRepository.save(cadet2);
        //---
        Cadet cadet3 = new Cadet();
        cadet3.setId(3);
        cadet3.setCaste(Caste.HOBO);
        cadet3.setChatId("908514277");
        cadet3.setDutyDayCount(0);
        cadet3.setLastName("Гвілдіс");
        cadet3.setStatus(CadetStatus.FREE);
        cadetRepository.save(cadet3);
        //---
        Cadet cadet4 = new Cadet();
        cadet4.setId(4);
        cadet4.setCaste(Caste.HOBO);
        cadet4.setChatId("864622010");
        cadet4.setDutyDayCount(0);
        cadet4.setLastName("Гетун");
        cadet4.setStatus(CadetStatus.FREE);
        cadetRepository.save(cadet4);
        //---
        Cadet cadet5 = new Cadet();
        cadet5.setId(5);
        cadet5.setCaste(Caste.SLUT);
        cadet5.setChatId("1811716955");
        cadet5.setDutyDayCount(0);
        cadet5.setLastName("Даценко");
        cadet5.setStatus(CadetStatus.FREE);
        cadetRepository.save(cadet5);
        //---
        Cadet cadet6 = new Cadet();
        cadet6.setId(6);
        cadet6.setCaste(Caste.HOBO);
        cadet6.setChatId("554880612");
        cadet6.setDutyDayCount(0);
        cadet6.setLastName("Кльоц");
        cadet6.setStatus(CadetStatus.FREE);
        cadetRepository.save(cadet6);
        //---
        Cadet cadet7 = new Cadet();
        cadet7.setId(7);
        cadet7.setCaste(Caste.HOBO);
        cadet7.setChatId("831022510");
        cadet7.setDutyDayCount(0);
        cadet7.setLastName("Литвяков");
        cadet7.setStatus(CadetStatus.FREE);
        cadetRepository.save(cadet7);
        //---
        Cadet cadet8 = new Cadet();
        cadet8.setId(8);
        cadet8.setCaste(Caste.HOBO);
        cadet8.setChatId("805427030");
        cadet8.setDutyDayCount(0);
        cadet8.setLastName("Мініч");
        cadet8.setStatus(CadetStatus.FREE);
        cadetRepository.save(cadet8);
        //---
        Cadet cadet9 = new Cadet();
        cadet9.setId(9);
        cadet9.setCaste(Caste.SLUT);
        cadet9.setChatId("1514302273");
        cadet9.setDutyDayCount(0);
        cadet9.setLastName("Селямієв");
        cadet9.setStatus(CadetStatus.FREE);
        cadetRepository.save(cadet9);
        //---
        Cadet cadet10 = new Cadet();
        cadet10.setId(10);
        cadet10.setCaste(Caste.SLUT);
        cadet10.setChatId("886992237");
        cadet10.setDutyDayCount(0);
        cadet10.setLastName("Сіваков");
        cadet10.setStatus(CadetStatus.FREE);
        cadetRepository.save(cadet10);
        //---
        Cadet cadet11 = new Cadet();
        cadet11.setId(11);
        cadet11.setCaste(Caste.SLUT);
        cadet11.setChatId("912894950");
        cadet11.setDutyDayCount(0);
        cadet11.setLastName("Хоменко");
        cadet11.setStatus(CadetStatus.FREE);
        cadetRepository.save(cadet11);
    }
}
