package com.training.controller;

import com.training.service.ReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reports")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:8080"})
public class ReportsController {
    
    @Autowired
    private ReportsService reportsService;
    
    @GetMapping("/availability-occupancy")
    public List<Map<String, Object>> getAvailabilityOccupancy() {
        return reportsService.getTrainerAvailabilityData();
    }
    
    @GetMapping("/batch-enrollments")
    public List<Map<String, Object>> getBatchEnrollments() {
        return reportsService.getBatchEnrollmentData();
    }
    
    @GetMapping("/attendance-trends")
    public List<Map<String, Object>> getAttendanceTrends() {
        return reportsService.getAttendanceTrendData();
    }
}
