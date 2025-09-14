package com.training.repository;

import com.training.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    List<Trainer> findByLocation(String location);
    
    @Query("SELECT t FROM Trainer t LEFT JOIN FETCH t.availability WHERE t.id = :id")
    Trainer findByIdWithAvailability(Long id);
}
