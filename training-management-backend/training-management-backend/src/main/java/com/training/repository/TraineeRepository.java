package com.training.repository;

import com.training.entity.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TraineeRepository extends JpaRepository<Trainee, Long> {
    List<Trainee> findByBatchId(Long batchId);
    
    @Query("SELECT t FROM Trainee t JOIN FETCH t.batch WHERE t.id = :id")
    Trainee findByIdWithBatch(Long id);
    
    @Query("SELECT COUNT(t) FROM Trainee t WHERE t.batch.id = :batchId")
    Long countByBatchId(Long batchId);
}
