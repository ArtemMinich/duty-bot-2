package com.dutybot2.dutybot2.service;

import com.dutybot2.dutybot2.dto.DutyDto;
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
public class DutyService {
    private final DutyRepository dutyRepository;
    private final CadetRepository cadetRepository;

    public List<Duty> findAll() {
        return dutyRepository.findAll();
    }

    @Transactional
    public void save(Duty duty) {
        duty.setDutyDate(LocalDate.now().plusDays(1));
        duty.getCubarCadet().incrementDutyDayCount();
        duty.getTerkaCadet().incrementDutyDayCount();
        dutyRepository.save(duty);
    }

    public Duty creatDuty(){
        Duty duty = new Duty();
        Optional<Cadet> terkaCadet =  creatTerkaCadet();
        terkaCadet.ifPresent(duty::setTerkaCadet);
        Optional<Cadet> cubarCadet =  creatCubarCadet();
        cubarCadet.ifPresent(duty::setCubarCadet);
        return duty;
    }

    private Optional<Cadet> creatTerkaCadet(){
        List<Cadet> hobos = cadetRepository.findAllByCaste(Caste.HOBO);
        return hobos.stream().filter(Cadet::isFree).min(Comparator.comparingInt(Cadet::getDutyDayCount));
    }
    private Optional<Cadet> creatCubarCadet(){
        List<Cadet> sluts = cadetRepository.findAllByCaste(Caste.SLUT);
        return sluts.stream().filter(Cadet::isFree).min(Comparator.comparingInt(Cadet::getDutyDayCount));
    }

    public DutyDto findDutyDtoByDutyDate(LocalDate date) {
        return dutyToDutyDtoMap(dutyRepository.findByDutyDate(date));
    }

    public DutyDto dutyToDutyDtoMap(Duty duty){
        return new DutyDto(duty.getDutyDate(),duty.getTerkaCadet().getLastName(),duty.getCubarCadet().getLastName());
    }

    public DutyDto updateDuty(LocalDate existingDutyDate, Duty updateDuty){
        Duty exitingDuty =  dutyRepository.getReferenceById(existingDutyDate);
        exitingDuty.setDutyDate(updateDuty.getDutyDate());
        exitingDuty.setTerkaCadet(updateDuty.getTerkaCadet());
        exitingDuty.setCubarCadet(updateDuty.getCubarCadet());
        return dutyToDutyDtoMap(exitingDuty);
    }

    public void deleteDuty(LocalDate dutyDate){
        dutyRepository.deleteById(dutyDate);
    }
}
