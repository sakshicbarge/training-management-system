package com.training.controller;

import com.training.entity.Attendance;
import com.training.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/attendance")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:8080"})
public class AttendanceController {
    
    @Autowired
    private AttendanceService attendanceService;
    
    @GetMapping
    public List<Attendance> getAllAttendance() {
        return attendanceService.getAllAttendance();
    }
    
    @PostMapping("/mark")
    public ResponseEntity<Attendance> markAttendance(@RequestBody Map<String, Object> attendanceData) {
        try {
            Long traineeId = Long.valueOf(attendanceData.get("traineeId").toString());
            Long batchId = Long.valueOf(attendanceData.get("batchId").toString());
            LocalDate sessionDate = LocalDate.parse(attendanceData.get("sessionDate").toString());
            Attendance.AttendanceStatus status = Attendance.AttendanceStatus.valueOf(attendanceData.get("status").toString());
            
            Attendance attendance = attendanceService.markAttendance(traineeId, batchId, sessionDate, status);
            return ResponseEntity.ok(attendance);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/trainee/{traineeId}")
    public List<Attendance> getAttendanceByTrainee(@PathVariable Long traineeId) {
        return attendanceService.getAttendanceByTrainee(traineeId);
    }
    
    @GetMapping("/batch/{batchId}")
    public List<Attendance> getAttendanceByBatch(@PathVariable Long batchId) {
        return attendanceService.getAttendanceByBatch(batchId);
    }
}
