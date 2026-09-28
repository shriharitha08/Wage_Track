package com.example.wagetrack.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.wagetrack.model.Attendance;

public interface AttendanceRepository
        extends JpaRepository<Attendance, Long> {

    List<Attendance> findByWorkerIdAndAttendanceDateBetween(
            Long workerId,
            LocalDate startDate,
            LocalDate endDate
    );
}