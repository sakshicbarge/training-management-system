package com.training.repository;

import com.training.entity.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Long> {
    List<Batch> findByLocation(String location);
    List<Batch> findByTrainerId(Long trainerId);
    
    @Query("SELECT b FROM Batch b LEFT JOIN FETCH b.trainees WHERE b.id = :id")
    Batch findByIdWithTrainees(Long id);
    
    @Query("SELECT b FROM Batch b JOIN FETCH b.trainer")
    List<Batch> findAllWithTrainer();
}
