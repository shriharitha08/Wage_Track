package com.example.wagetrack.repository;

import com.example.wagetrack.model.Worksite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorksiteRepository extends JpaRepository<Worksite, Long> {
}
