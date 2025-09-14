package com.training.service;

import com.training.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportsService {
    
    @Autowired
    private TrainerRepository trainerRepository;
    
    @Autowired
    private TrainerAvailabilityRepository availabilityRepository;
    
    @Autowired
    private TraineeRepository traineeRepository;
    
    @Autowired
    private BatchRepository batchRepository;
    
    @Autowired
    private AttendanceRepository attendanceRepository;
    
    public List<Map<String, Object>> getTrainerAvailabilityData() {
        List<Map<String, Object>> result = new ArrayList<>();
        
        List<com.training.entity.Trainer> trainers = trainerRepository.findAll();
        
        for (com.training.entity.Trainer trainer : trainers) {
            Map<String, Object> data = new HashMap<>();
            data.put("trainerName", trainer.getName());
            data.put("location", trainer.getLocation());
            
            Long availableSlots = availabilityRepository.countByTrainerIdAndIsAssigned(trainer.getId(), false);
            Long assignedSlots = availabilityRepository.countByTrainerIdAndIsAssigned(trainer.getId(), true);
            
            data.put("available", availableSlots != null ? availableSlots : 0);
            data.put("assigned", assignedSlots != null ? assignedSlots : 0);
            
            result.add(data);
        }
        
        return result;
    }
    
    public List<Map<String, Object>> getBatchEnrollmentData() {
        List<Map<String, Object>> result = new ArrayList<>();
        
        List<com.training.entity.Batch> batches = batchRepository.findAllWithTrainer();
        
        for (com.training.entity.Batch batch : batches) {
            Map<String, Object> data = new HashMap<>();
            data.put("batchName", batch.getCourse());
            data.put("location", batch.getLocation());
            data.put("trainerName", batch.getTrainer() != null ? batch.getTrainer().getName() : "Unassigned");
            
            Long enrollmentCount = traineeRepository.countByBatchId(batch.getId());
            data.put("enrollments", enrollmentCount != null ? enrollmentCount : 0);
            
            result.add(data);
        }
        
        return result;
    }
    
    public List<Map<String, Object>> getAttendanceTrendData() {
        LocalDate startDate = LocalDate.now().minusWeeks(4);
        LocalDate endDate = LocalDate.now();
        
        List<com.training.entity.Attendance> attendanceRecords = 
            attendanceRepository.findAttendanceWithDetailsInDateRange(startDate, endDate);
        
        Map<LocalDate, Long> attendanceByDate = attendanceRecords.stream()
            .filter(a -> a.getStatus() == com.training.entity.Attendance.AttendanceStatus.present)
            .collect(Collectors.groupingBy(
                com.training.entity.Attendance::getSessionDate,
                Collectors.counting()
            ));
        
        List<Map<String, Object>> result = new ArrayList<>();
        
        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {
            Map<String, Object> data = new HashMap<>();
            data.put("date", currentDate.toString());
            data.put("presentCount", attendanceByDate.getOrDefault(currentDate, 0L));
            result.add(data);
            currentDate = currentDate.plusDays(1);
        }
        
        return result;
    }
}
