package com.dutybot2.dutybot2.service;

import com.dutybot2.dutybot2.model.Cadet;
import com.dutybot2.dutybot2.model.Duty;
import com.dutybot2.dutybot2.repository.CadetRepository;
import com.dutybot2.dutybot2.repository.DutyRepository;
import com.dutybot2.dutybot2.util.Caste;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CadetService {

    private CadetRepository cadetRepository;

    private DutyRepository dutyRepository;

    public Duty creatDuty(){
        Duty duty = new Duty();
        Optional<Cadet> terkaCadet =  creatTerkaCadet();
        terkaCadet.ifPresent(duty::setTerkaCadet);
        Optional<Cadet> cubarCadet =  creatCubarCadet();
        cubarCadet.ifPresent(duty::setCubarCadet);
        return duty;
    }

    @Transactional
    public void saveDuty(Duty duty){
        duty.setDutyDate(LocalDate.now().plusDays(1));
        duty.getCubarCadet().incrementDutyDayCount();
        duty.getTerkaCadet().incrementDutyDayCount();
        dutyRepository.save(duty);
    }

    private Optional<Cadet> creatTerkaCadet(){
        List<Cadet> hobos = cadetRepository.findAllByCaste(Caste.HOBO);
        return hobos.stream().filter(Cadet::isFree).min(Comparator.comparingInt(Cadet::getDutyDayCount));
    }
    private Optional<Cadet> creatCubarCadet(){
        List<Cadet> sluts = cadetRepository.findAllByCaste(Caste.SLUT);
        return sluts.stream().filter(Cadet::isFree).min(Comparator.comparingInt(Cadet::getDutyDayCount));
    }
}
