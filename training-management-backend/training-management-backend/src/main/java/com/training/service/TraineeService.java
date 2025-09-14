package com.training.service;

import com.training.entity.Trainee;
import com.training.entity.Batch;
import com.training.repository.TraineeRepository;
import com.training.repository.BatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TraineeService {
    
    @Autowired
    private TraineeRepository traineeRepository;
    
    @Autowired
    private BatchRepository batchRepository;
    
    public List<Trainee> getAllTrainees() {
        return traineeRepository.findAll();
    }
    
    public Optional<Trainee> getTraineeById(Long id) {
        return traineeRepository.findById(id);
    }
    
    public Trainee saveTrainee(Trainee trainee) {
        return traineeRepository.save(trainee);
    }
    
    public Trainee enrollTrainee(Long traineeId, Long batchId) {
        Optional<Trainee> traineeOpt = traineeRepository.findById(traineeId);
        Optional<Batch> batchOpt = batchRepository.findById(batchId);
        
        if (traineeOpt.isPresent() && batchOpt.isPresent()) {
            Trainee trainee = traineeOpt.get();
            trainee.setBatch(batchOpt.get());
            return traineeRepository.save(trainee);
        }
        throw new RuntimeException("Trainee or Batch not found");
    }
    
    public List<Trainee> getTraineesByBatch(Long batchId) {
        return traineeRepository.findByBatchId(batchId);
    }
}
