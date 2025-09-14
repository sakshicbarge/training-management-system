package com.training.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Trainee")
public class Trainee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    private String email;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "batch_id")
    @JsonBackReference("batch-trainee")
    private Batch batch;
    
    @Column(name = "enrollment_date")
    private LocalDateTime enrollmentDate = LocalDateTime.now();
    
    @OneToMany(mappedBy = "trainee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("trainee-attendance")
    private List<Attendance> attendanceRecords;
    
    // Constructors, getters and setters remain the same
    public Trainee() {}
    
    public Trainee(String name, String email) {
        this.name = name;
        this.email = email;
    }
    
    // All getters and setters remain the same...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public Batch getBatch() { return batch; }
    public void setBatch(Batch batch) { this.batch = batch; }
    
    public LocalDateTime getEnrollmentDate() { return enrollmentDate; }
    public void setEnrollmentDate(LocalDateTime enrollmentDate) { this.enrollmentDate = enrollmentDate; }
    
    public List<Attendance> getAttendanceRecords() { return attendanceRecords; }
    public void setAttendanceRecords(List<Attendance> attendanceRecords) { this.attendanceRecords = attendanceRecords; }
}
