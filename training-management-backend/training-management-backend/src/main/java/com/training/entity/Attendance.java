package com.training.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainee_id", nullable = false)
    @JsonBackReference("trainee-attendance")
    private Trainee trainee;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "batch_id", nullable = false)
    @JsonBackReference("batch-attendance")
    private Batch batch;
    
    @Column(name = "session_date", nullable = false)
    private LocalDate sessionDate;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AttendanceStatus status;
    
    @Column(name = "marked_at")
    private LocalDateTime markedAt = LocalDateTime.now();
    
    // Enum for attendance status
    public enum AttendanceStatus {
        present, absent
    }
    
    // Constructors, getters and setters remain the same
    public Attendance() {}
    
    public Attendance(Trainee trainee, Batch batch, LocalDate sessionDate, AttendanceStatus status) {
        this.trainee = trainee;
        this.batch = batch;
        this.sessionDate = sessionDate;
        this.status = status;
    }
    
    // All getters and setters remain the same...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Trainee getTrainee() { return trainee; }
    public void setTrainee(Trainee trainee) { this.trainee = trainee; }
    
    public Batch getBatch() { return batch; }
    public void setBatch(Batch batch) { this.batch = batch; }
    
    public LocalDate getSessionDate() { return sessionDate; }
    public void setSessionDate(LocalDate sessionDate) { this.sessionDate = sessionDate; }
    
    public AttendanceStatus getStatus() { return status; }
    public void setStatus(AttendanceStatus status) { this.status = status; }
    
    public LocalDateTime getMarkedAt() { return markedAt; }
    public void setMarkedAt(LocalDateTime markedAt) { this.markedAt = markedAt; }
}
