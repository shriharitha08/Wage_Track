package com.example.wagetrack.controller;

import com.example.wagetrack.model.Payment;
import com.example.wagetrack.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/worker/{workerId}")
    public List<Payment> getWorkerPayments(
            @PathVariable Long workerId) {

        return paymentService.getWorkerPayments(workerId);
    }

    @PostMapping
    public Payment addPayment(
            @Valid @RequestBody Payment payment,
            @RequestParam Long workerId) {

        return paymentService.addPayment(payment, workerId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePayment(
            @PathVariable Long id) {

        paymentService.deletePayment(id);

        return ResponseEntity.ok(
                "Payment deleted successfully"
        );
    }
}