package com.dutybot2.dutybot2.api;

import com.dutybot2.dutybot2.model.Cadet;
import com.dutybot2.dutybot2.model.Duty;
import com.dutybot2.dutybot2.repository.DutyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/duties")
@AllArgsConstructor
public class DutyRestController {

    private final DutyRepository dutyRepository;

    @GetMapping
    public ResponseEntity<Object> getAllDuties(){
        try{
            List<Duty> duties = dutyRepository.findAll();
            return  ResponseEntity.ok(duties);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred while fetching student");
        }
    }

    @PostMapping
    public ResponseEntity<Object> saveDuty(@RequestBody Duty newDuty){
        try{
            dutyRepository.save(newDuty);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("")
                    .buildAndExpand()
                    .toUri());
            return new ResponseEntity<>(newDuty, httpHeaders, HttpStatus.CREATED);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred while fetching student");
        }
    }

    @GetMapping("/duty")
    public ResponseEntity<Object> getDuty(@RequestParam("date") String date){
        try{
            Duty duty = dutyRepository.getDutyByDutyDate(LocalDate.parse(date));
            return  ResponseEntity.ok(duty);
        }catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred while fetching student");
        }
    }
}
