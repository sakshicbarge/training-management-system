package com.training.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "TrainerAvailability")
public class TrainerAvailability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "trainer_id", nullable = false)
    @JsonBackReference("traineravailability")
    private Trainer trainer;
    
    @Column(name = "available_date", nullable = false)
    private LocalDate availableDate;
    
    @Column(name = "time_slot", nullable = false)
    private String timeSlot;
    
    @Column(name = "is_assigned")
    private Boolean isAssigned = false;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    
    // Constructors, getters and setters remain the same
    public TrainerAvailability() {}
    
    public TrainerAvailability(Trainer trainer, LocalDate availableDate, String timeSlot) {
        this.trainer = trainer;
        this.availableDate = availableDate;
        this.timeSlot = timeSlot;
    }
    
    // All getters and setters remain the same...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Trainer getTrainer() { return trainer; }
    public void setTrainer(Trainer trainer) { this.trainer = trainer; }
    
    public LocalDate getAvailableDate() { return availableDate; }
    public void setAvailableDate(LocalDate availableDate) { this.availableDate = availableDate; }
    
    public String getTimeSlot() { return timeSlot; }
    public void setTimeSlot(String timeSlot) { this.timeSlot = timeSlot; }
    
    public Boolean getIsAssigned() { return isAssigned; }
    public void setIsAssigned(Boolean isAssigned) { this.isAssigned = isAssigned; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
