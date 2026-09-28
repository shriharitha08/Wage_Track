package com.example.wagetrack.service;

import com.example.wagetrack.model.Attendance;
import com.example.wagetrack.model.WageSummary;
import com.example.wagetrack.model.Worker;
import com.example.wagetrack.repository.AttendanceRepository;
import com.example.wagetrack.repository.WorkerRepository;
import com.example.wagetrack.repository.WageSummaryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class WageSummaryService {

    private final WageSummaryRepository wageSummaryRepository;
    private final AttendanceRepository attendanceRepository;
    private final WorkerRepository workerRepository;

    public WageSummaryService(
            WageSummaryRepository wageSummaryRepository,
            AttendanceRepository attendanceRepository,
            WorkerRepository workerRepository) {

        this.wageSummaryRepository = wageSummaryRepository;
        this.attendanceRepository = attendanceRepository;
        this.workerRepository = workerRepository;
    }

    public WageSummary generateWeeklySummary(
            Long workerId,
            LocalDate start,
            LocalDate end) {

        Worker worker = workerRepository.findById(workerId)
                .orElseThrow(() -> new RuntimeException("Worker not found"));

        List<Attendance> records =
                attendanceRepository.findByWorkerIdAndAttendanceDateBetween(
                        workerId, start, end);

        int fullDays = 0;
        int halfDays = 0;
        int absentDays = 0;

        double overtimeHours = 0;
        double regularWage = 0;
        double overtimePay = 0;

        for (Attendance attendance : records) {

            if ("FULL_DAY".equalsIgnoreCase(
                    attendance.getAttendanceType())) {

                fullDays++;
                regularWage += worker.getDailyWage();

            } else if ("HALF_DAY".equalsIgnoreCase(
                    attendance.getAttendanceType())) {

                halfDays++;
                regularWage += worker.getDailyWage() / 2;

            } else if ("ABSENT".equalsIgnoreCase(
                    attendance.getAttendanceType())) {

                absentDays++;
            }

            overtimeHours += attendance.getOvertimeHours();
            overtimePay += attendance.getOvertimePay();
        }

        double totalPayable = regularWage + overtimePay;

        WageSummary summary = new WageSummary();

        summary.setWorker(worker);
        summary.setWeekStart(start);
        summary.setWeekEnd(end);
        summary.setFullDays(fullDays);
        summary.setHalfDays(halfDays);
        summary.setAbsentDays(absentDays);
        summary.setOvertimeHours(overtimeHours);
        summary.setRegularWage(regularWage);
        summary.setOvertimePay(overtimePay);
        summary.setTotalPayable(totalPayable);

        return wageSummaryRepository.save(summary);
    }

    public List<WageSummary> getAllSummaries() {
        return wageSummaryRepository.findAll();
    }
}