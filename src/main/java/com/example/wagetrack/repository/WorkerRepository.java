package com.example.wagetrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.wagetrack.model.Worker;

public interface WorkerRepository
        extends JpaRepository<Worker, Long> {
}