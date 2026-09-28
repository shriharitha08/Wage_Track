package com.example.wagetrack.controller;

import com.example.wagetrack.model.WageSummary;
import com.example.wagetrack.service.WageSummaryService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/wages")
@CrossOrigin
public class WageSummaryController {

    private final WageSummaryService wageSummaryService;

    public WageSummaryController(
            WageSummaryService wageSummaryService) {

        this.wageSummaryService = wageSummaryService;
    }

    @GetMapping
    public List<WageSummary> getAllSummaries() {
        return wageSummaryService.getAllSummaries();
    }

    @PostMapping("/weekly/{workerId}")
    public WageSummary generateWeeklySummary(
            @PathVariable Long workerId,
            @RequestParam String start,
            @RequestParam String end) {

        return wageSummaryService.generateWeeklySummary(
                workerId,
                LocalDate.parse(start),
                LocalDate.parse(end)
        );
    }
}