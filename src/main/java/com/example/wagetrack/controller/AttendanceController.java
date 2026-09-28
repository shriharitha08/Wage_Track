package com.example.wagetrack.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.wagetrack.model.Attendance;
import com.example.wagetrack.service.AttendanceService;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(
            AttendanceService attendanceService) {

        this.attendanceService = attendanceService;
    }

    @GetMapping
    public List<Attendance> getAll() {
        return attendanceService.getAllAttendance();
    }

    @GetMapping("/{id}")
    public Attendance getOne(@PathVariable Long id) {
        return attendanceService.getAttendanceById(id);
    }

    @PostMapping
    public Attendance add(
            @RequestBody Attendance attendance,
            @RequestParam Long workerId,
            @RequestParam Long worksiteId) {

        return attendanceService.addAttendance(
                attendance,
                workerId,
                worksiteId);
    }

    @PutMapping("/{id}")
    public Attendance update(
            @PathVariable Long id,
            @RequestBody Attendance attendance) {

        return attendanceService.updateAttendance(
                id,
                attendance);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        attendanceService.deleteAttendance(id);
    }
}