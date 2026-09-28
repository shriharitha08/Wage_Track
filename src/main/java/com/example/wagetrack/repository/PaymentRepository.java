package com.example.wagetrack.repository;

import com.example.wagetrack.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByWorkerId(Long workerId);
}