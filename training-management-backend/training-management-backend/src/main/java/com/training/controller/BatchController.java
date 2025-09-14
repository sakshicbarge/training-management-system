package com.training.controller;

import com.training.entity.Batch;
import com.training.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/batches")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:8080"})
public class BatchController {
    
    @Autowired
    private BatchService batchService;
    
    @GetMapping
    public List<Batch> getAllBatches() {
        return batchService.getAllBatches();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Batch> getBatchById(@PathVariable Long id) {
        return batchService.getBatchById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/{id}/details")
    public ResponseEntity<Batch> getBatchDetails(@PathVariable Long id) {
        Batch batch = batchService.getBatchWithDetails(id);
        return batch != null ? ResponseEntity.ok(batch) : ResponseEntity.notFound().build();
    }
    
    @PostMapping
    public Batch createBatch(@RequestBody Batch batch) {
        return batchService.saveBatch(batch);
    }
    
    @GetMapping("/location/{location}")
    public List<Batch> getBatchesByLocation(@PathVariable String location) {
        return batchService.getBatchesByLocation(location);
    }
}
