package com.training.controller;

import com.training.entity.Trainer;
import com.training.entity.TrainerAvailability;
import com.training.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainers")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:8080"})
public class TrainerController {
    
    @Autowired
    private TrainerService trainerService;
    
    @GetMapping
    public List<Trainer> getAllTrainers() {
        return trainerService.getAllTrainers();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Trainer> getTrainerById(@PathVariable Long id) {
        return trainerService.getTrainerById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Trainer createTrainer(@RequestBody Trainer trainer) {
        return trainerService.saveTrainer(trainer);
    }
    
    @GetMapping("/availability")
    public List<TrainerAvailability> getAllAvailability() {
        return trainerService.getAllAvailability();
    }
    
    @PostMapping("/availability")
    public TrainerAvailability addAvailability(@RequestBody TrainerAvailability availability) {
        return trainerService.addAvailability(availability);
    }
    
    @GetMapping("/{id}/availability")
    public List<TrainerAvailability> getTrainerAvailability(@PathVariable Long id) {
        return trainerService.getTrainerAvailability(id);
    }
}
