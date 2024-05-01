package com.dutybot2.dutybot2.api;

import com.dutybot2.dutybot2.dto.DutyDto;
import com.dutybot2.dutybot2.model.Cadet;
import com.dutybot2.dutybot2.model.Duty;
import com.dutybot2.dutybot2.repository.DutyRepository;
import com.dutybot2.dutybot2.service.CadetService;
import com.dutybot2.dutybot2.service.DutyService;
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

    private final DutyService dutyService;

    @GetMapping
    public ResponseEntity<Object> getAllDutiesDto(){
        try{
            List<DutyDto> duties = dutyService.findAll().stream().map(dutyService::dutyToDutyDtoMap).toList();
            return  ResponseEntity.ok(duties);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> saveDuty(@RequestBody Duty newDuty){
        try{
            dutyService.save(newDuty);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("")
                    .buildAndExpand()
                    .toUri());
            return new ResponseEntity<>(newDuty, httpHeaders, HttpStatus.CREATED);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/duty")
    public ResponseEntity<Object> getDutyDto(@RequestParam("date") String date){
        try{
            DutyDto dutyDto = dutyService.findDutyDtoByDutyDate(LocalDate.parse(date));
            return  ResponseEntity.ok(dutyDto);
        }catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
