package com.example.wagetrack.repository;

import com.example.wagetrack.model.WageSummary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WageSummaryRepository extends JpaRepository<WageSummary, Long> {
}