package com.training.service;

import com.training.entity.Attendance;
import com.training.entity.Trainee;
import com.training.entity.Batch;
import com.training.repository.AttendanceRepository;
import com.training.repository.TraineeRepository;
import com.training.repository.BatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {
    
    @Autowired
    private AttendanceRepository attendanceRepository;
    
    @Autowired
    private TraineeRepository traineeRepository;
    
    @Autowired
    private BatchRepository batchRepository;
    
    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }
    
    public Attendance markAttendance(Long traineeId, Long batchId, LocalDate sessionDate, Attendance.AttendanceStatus status) {
        Optional<Trainee> traineeOpt = traineeRepository.findById(traineeId);
        Optional<Batch> batchOpt = batchRepository.findById(batchId);
        
        if (traineeOpt.isPresent() && batchOpt.isPresent()) {
            Attendance attendance = new Attendance(traineeOpt.get(), batchOpt.get(), sessionDate, status);
            return attendanceRepository.save(attendance);
        }
        throw new RuntimeException("Trainee or Batch not found");
    }
    
    public List<Attendance> getAttendanceByTrainee(Long traineeId) {
        return attendanceRepository.findByTraineeId(traineeId);
    }
    
    public List<Attendance> getAttendanceByBatch(Long batchId) {
        return attendanceRepository.findByBatchId(batchId);
    }
    
    public List<Attendance> getAttendanceInDateRange(LocalDate startDate, LocalDate endDate) {
        return attendanceRepository.findAttendanceWithDetailsInDateRange(startDate, endDate);
    }
}
