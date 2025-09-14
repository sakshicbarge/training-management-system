package com.training.repository;

import com.training.entity.TrainerAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TrainerAvailabilityRepository extends JpaRepository<TrainerAvailability, Long> {
    List<TrainerAvailability> findByTrainerIdAndAvailableDateBetween(Long trainerId, LocalDate startDate, LocalDate endDate);
    
    @Query("SELECT ta FROM TrainerAvailability ta JOIN FETCH ta.trainer")
    List<TrainerAvailability> findAllWithTrainer();
    
    @Query("SELECT COUNT(ta) FROM TrainerAvailability ta WHERE ta.trainer.id = :trainerId AND ta.isAssigned = :isAssigned")
    Long countByTrainerIdAndIsAssigned(Long trainerId, Boolean isAssigned);
}
