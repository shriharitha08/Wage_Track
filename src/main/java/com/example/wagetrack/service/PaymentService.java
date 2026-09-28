package com.example.wagetrack.service;

import com.example.wagetrack.model.Payment;
import com.example.wagetrack.model.Worker;
import com.example.wagetrack.repository.PaymentRepository;
import com.example.wagetrack.repository.WorkerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final WorkerRepository workerRepository;

    public PaymentService(
            PaymentRepository paymentRepository,
            WorkerRepository workerRepository) {

        this.paymentRepository = paymentRepository;
        this.workerRepository = workerRepository;
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment addPayment(
            Payment payment,
            Long workerId) {

        Worker worker = workerRepository.findById(workerId)
                .orElseThrow(() -> new RuntimeException("Worker not found"));

        payment.setWorker(worker);

        return paymentRepository.save(payment);
    }

    public List<Payment> getWorkerPayments(Long workerId) {
        return paymentRepository.findByWorkerId(workerId);
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }
}