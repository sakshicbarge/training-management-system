package com.training.repository;

import com.training.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByTraineeId(Long traineeId);
    List<Attendance> findByBatchId(Long batchId);
    List<Attendance> findBySessionDateBetween(LocalDate startDate, LocalDate endDate);
    
    @Query("SELECT COUNT(a) FROM Attendance a WHERE a.batch.id = :batchId AND a.sessionDate = :date AND a.status = 'present'")
    Long countPresentByBatchIdAndDate(Long batchId, LocalDate date);
    
    @Query("SELECT a FROM Attendance a JOIN FETCH a.trainee JOIN FETCH a.batch WHERE a.sessionDate BETWEEN :startDate AND :endDate")
    List<Attendance> findAttendanceWithDetailsInDateRange(LocalDate startDate, LocalDate endDate);
}
