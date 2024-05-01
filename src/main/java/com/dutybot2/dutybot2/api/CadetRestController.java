package com.dutybot2.dutybot2.api;

import com.dutybot2.dutybot2.model.Cadet;
import com.dutybot2.dutybot2.repository.CadetRepository;
import com.dutybot2.dutybot2.service.CadetService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cadets")
@AllArgsConstructor
public class CadetRestController {
    private final CadetService cadetService;

    @GetMapping
    public ResponseEntity<Object> getAllCadets(){
        try{
            List<Cadet> cadets = cadetService.findAll();
            return  ResponseEntity.ok(cadets);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred while fetching student");
        }
    }

    @PostMapping
    public ResponseEntity<Object> saveCadet(@RequestBody Cadet newCadet){
        try{
            cadetService.save(newCadet);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(newCadet.getId())
                    .toUri());
            return new ResponseEntity<>(newCadet, httpHeaders, HttpStatus.CREATED);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred while fetching student");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCadetById(@PathVariable("id") int id){
        try{
            Cadet cadet = cadetService.getReferenceById(id);
            return  ResponseEntity.ok(cadet);
        }catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred while fetching student");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCadetById(@PathVariable("id") int id,
                                               @RequestBody Cadet updateCadet){
        try{
            Cadet cadet = cadetService.updateCadet(id, updateCadet);
            return  ResponseEntity.ok(cadet);
        }catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred while fetching student");
        }
    }
}
