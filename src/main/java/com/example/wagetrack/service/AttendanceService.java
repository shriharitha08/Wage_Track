package com.example.wagetrack.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.wagetrack.model.Attendance;
import com.example.wagetrack.model.Worker;
import com.example.wagetrack.model.Worksite;
import com.example.wagetrack.repository.AttendanceRepository;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final WorkerService workerService;
    private final WorksiteService worksiteService;

    public AttendanceService(
            AttendanceRepository attendanceRepository,
            WorkerService workerService,
            WorksiteService worksiteService) {

        this.attendanceRepository = attendanceRepository;
        this.workerService = workerService;
        this.worksiteService = worksiteService;
    }

    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }

    public Attendance getAttendanceById(Long id) {
        return attendanceRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Attendance not found"));
    }

    public Attendance addAttendance(
            Attendance attendance,
            Long workerId,
            Long worksiteId) {

        Worker worker =
                workerService.getWorkerById(workerId);

        Worksite worksite =
                worksiteService.getWorksiteById(worksiteId);

        attendance.setWorker(worker);
        attendance.setWorksite(worksite);

        calculatePayment(attendance, worker);

        return attendanceRepository.save(attendance);
    }

    public Attendance updateAttendance(
            Long id,
            Attendance updated) {

        Attendance existing =
                getAttendanceById(id);

        existing.setAttendanceDate(
                updated.getAttendanceDate());

        existing.setAttendanceType(
                updated.getAttendanceType());

        existing.setWorkedHours(
                updated.getWorkedHours());

        calculatePayment(
                existing,
                existing.getWorker());

        return attendanceRepository.save(existing);
    }

    private void calculatePayment(
            Attendance attendance,
            Worker worker) {

        double dailyWage =
                worker.getDailyWage();

        double standardHours =
                worker.getStandardWorkHours();

        double workedHours =
                attendance.getWorkedHours();

        double dailyPay = 0;

        if ("FULL_DAY".equalsIgnoreCase(
                attendance.getAttendanceType())) {

            dailyPay = dailyWage;

        } else if ("HALF_DAY".equalsIgnoreCase(
                attendance.getAttendanceType())) {

            dailyPay = dailyWage / 2;
        }

        double overtimeHours = 0;

        if (workedHours > standardHours) {
            overtimeHours =
                    workedHours - standardHours;
        }

        double hourlyWage =
                standardHours > 0
                        ? dailyWage / standardHours
                        : 0;

        double overtimePay =
                overtimeHours * hourlyWage * 1.5;

        attendance.setDailyPay(dailyPay);
        attendance.setOvertimeHours(overtimeHours);
        attendance.setOvertimePay(overtimePay);
    }

    public void deleteAttendance(Long id) {
        attendanceRepository.deleteById(id);
    }
}