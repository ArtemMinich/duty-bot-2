package com.dutybot2.dutybot2.repository;

import com.dutybot2.dutybot2.model.Duty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;

public interface DutyRepository extends JpaRepository<Duty, LocalDate> {
    Duty findByDutyDate(LocalDate date);
}