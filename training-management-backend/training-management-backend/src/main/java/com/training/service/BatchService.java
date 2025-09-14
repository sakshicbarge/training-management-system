package com.training.service;

import com.training.entity.Batch;
import com.training.repository.BatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BatchService {
    
    @Autowired
    private BatchRepository batchRepository;
    
    public List<Batch> getAllBatches() {
        return batchRepository.findAllWithTrainer();
    }
    
    public Optional<Batch> getBatchById(Long id) {
        return batchRepository.findById(id);
    }
    
    public Batch getBatchWithDetails(Long id) {
        return batchRepository.findByIdWithTrainees(id);
    }
    
    public Batch saveBatch(Batch batch) {
        return batchRepository.save(batch);
    }
    
    public List<Batch> getBatchesByLocation(String location) {
        return batchRepository.findByLocation(location);
    }
}
