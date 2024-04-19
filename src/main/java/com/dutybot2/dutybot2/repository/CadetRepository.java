package com.dutybot2.dutybot2.repository;

import com.dutybot2.dutybot2.model.Cadet;
import com.dutybot2.dutybot2.service.CadetDetailsService;
import com.dutybot2.dutybot2.util.Caste;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface CadetRepository extends JpaRepository<Cadet, Integer> {
    List<Cadet> findAllByCaste(Caste caste);

}