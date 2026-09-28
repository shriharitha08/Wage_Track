package com.example.wagetrack.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate attendanceDate;

    private String attendanceType;

    private double workedHours;

    private double overtimeHours;

    private double overtimePay;

    private double dailyPay;

    @ManyToOne
    @JoinColumn(name = "worker_id")
    @JsonIgnoreProperties({"attendances", "payments"})
    private Worker worker;

    @ManyToOne
    @JoinColumn(name = "worksite_id")
    @JsonIgnoreProperties({"attendances"})
    private Worksite worksite;

    public Attendance() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(LocalDate attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public String getAttendanceType() {
        return attendanceType;
    }

    public void setAttendanceType(String attendanceType) {
        this.attendanceType = attendanceType;
    }

    public double getWorkedHours() {
        return workedHours;
    }

    public void setWorkedHours(double workedHours) {
        this.workedHours = workedHours;
    }

    public double getOvertimeHours() {
        return overtimeHours;
    }

    public void setOvertimeHours(double overtimeHours) {
        this.overtimeHours = overtimeHours;
    }

    public double getOvertimePay() {
        return overtimePay;
    }

    public void setOvertimePay(double overtimePay) {
        this.overtimePay = overtimePay;
    }

    public double getDailyPay() {
        return dailyPay;
    }

    public void setDailyPay(double dailyPay) {
        this.dailyPay = dailyPay;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public Worksite getWorksite() {
        return worksite;
    }

    public void setWorksite(Worksite worksite) {
        this.worksite = worksite;
    }
}