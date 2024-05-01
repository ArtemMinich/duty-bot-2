package com.dutybot2.dutybot2.api;

import com.dutybot2.dutybot2.dto.CadetDto;
import com.dutybot2.dutybot2.model.Cadet;
import com.dutybot2.dutybot2.service.CadetService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping("/api/cadets")
@AllArgsConstructor
public class CadetRestController {
    private final CadetService cadetService;

    @GetMapping
    public ResponseEntity<Object> getAllCadetDtos(){
        try{
            return  ResponseEntity.ok(cadetService.findAll().stream().map(cadetService::cadetToCadetDtoMap).toList());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
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
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CadetDto> getCadetDtoById(@PathVariable("id") int id){
        try{
            return  ResponseEntity.ok(cadetService.findCadetDtoById(id));
        }catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCadetById(@PathVariable("id") int id,
                                               @RequestBody Cadet updateCadet){
        try{
            return  ResponseEntity.ok(cadetService.updateCadet(id, updateCadet));
        }catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCadetById(@PathVariable("id") int id){
        try{
            cadetService.deleteCadetById(id);
            return  ResponseEntity.ok(HttpStatus.OK);
        }catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
