package com.training.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Trainer")
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String location;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("trainer-availability")
    private List<TrainerAvailability> availability;
    
    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("trainer-batch")
    private List<Batch> batches;
    
    // Constructors, getters and setters remain the same
    public Trainer() {}
    
    public Trainer(String name, String location) {
        this.name = name;
        this.location = location;
    }
    
    // All getters and setters remain the same...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public List<TrainerAvailability> getAvailability() { return availability; }
    public void setAvailability(List<TrainerAvailability> availability) { this.availability = availability; }
    
    public List<Batch> getBatches() { return batches; }
    public void setBatches(List<Batch> batches) { this.batches = batches; }
}
