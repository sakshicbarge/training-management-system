package com.training.controller;

import com.training.entity.Trainee;
import com.training.service.TraineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/trainees")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:8080"})
public class TraineeController {
    
    @Autowired
    private TraineeService traineeService;
    
    @GetMapping
    public List<Trainee> getAllTrainees() {
        return traineeService.getAllTrainees();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Trainee> getTraineeById(@PathVariable Long id) {
        return traineeService.getTraineeById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Trainee createTrainee(@RequestBody Trainee trainee) {
        return traineeService.saveTrainee(trainee);
    }
    
    @PostMapping("/enroll")
    public ResponseEntity<Trainee> enrollTrainee(@RequestBody Map<String, Long> enrollmentData) {
        try {
            Long traineeId = enrollmentData.get("traineeId");
            Long batchId = enrollmentData.get("batchId");
            Trainee enrolledTrainee = traineeService.enrollTrainee(traineeId, batchId);
            return ResponseEntity.ok(enrolledTrainee);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/batch/{batchId}")
    public List<Trainee> getTraineesByBatch(@PathVariable Long batchId) {
        return traineeService.getTraineesByBatch(batchId);
    }
}
