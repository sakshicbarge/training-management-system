package com.training.service;

import com.training.entity.Trainer;
import com.training.entity.TrainerAvailability;
import com.training.repository.TrainerRepository;
import com.training.repository.TrainerAvailabilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainerService {
    
    @Autowired
    private TrainerRepository trainerRepository;
    
    @Autowired
    private TrainerAvailabilityRepository availabilityRepository;
    
    public List<Trainer> getAllTrainers() {
        return trainerRepository.findAll();
    }
    
    public Optional<Trainer> getTrainerById(Long id) {
        return trainerRepository.findById(id);
    }
    
    public Trainer saveTrainer(Trainer trainer) {
        return trainerRepository.save(trainer);
    }
    
    public List<TrainerAvailability> getAllAvailability() {
        return availabilityRepository.findAllWithTrainer();
    }
    
    public TrainerAvailability addAvailability(TrainerAvailability availability) {
        return availabilityRepository.save(availability);
    }
    
    public List<TrainerAvailability> getTrainerAvailability(Long trainerId) {
        return availabilityRepository.findByTrainerIdAndAvailableDateBetween(
            trainerId, 
            java.time.LocalDate.now(), 
            java.time.LocalDate.now().plusMonths(1)
        );
    }
}
