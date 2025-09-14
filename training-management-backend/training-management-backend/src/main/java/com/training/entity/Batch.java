package com.training.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Batch")
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String course;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id")
    @JsonBackReference("trainer-batch")
    private Trainer trainer;
    
    @Column(nullable = false)
    private String location;
    
    @Column(name = "start_date")
    private LocalDate startDate;
    
    @Column(name = "end_date")
    private LocalDate endDate;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @OneToMany(mappedBy = "batch", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("batch-trainee")
    private List<Trainee> trainees;
    
    @OneToMany(mappedBy = "batch", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("batch-attendance")
    private List<Attendance> attendanceRecords;
    
    // Constructors, getters and setters remain the same
    public Batch() {}
    
    public Batch(String course, String location, LocalDate startDate, LocalDate endDate) {
        this.course = course;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    // All getters and setters remain the same...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }
    
    public Trainer getTrainer() { return trainer; }
    public void setTrainer(Trainer trainer) { this.trainer = trainer; }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public List<Trainee> getTrainees() { return trainees; }
    public void setTrainees(List<Trainee> trainees) { this.trainees = trainees; }
    
    public List<Attendance> getAttendanceRecords() { return attendanceRecords; }
    public void setAttendanceRecords(List<Attendance> attendanceRecords) { this.attendanceRecords = attendanceRecords; }
}
