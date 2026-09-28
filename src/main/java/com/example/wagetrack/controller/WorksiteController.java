package com.example.wagetrack.controller;

import com.example.wagetrack.model.Worksite;
import com.example.wagetrack.service.WorksiteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/worksites")
@CrossOrigin
public class WorksiteController {

    private final WorksiteService worksiteService;

    public WorksiteController(WorksiteService worksiteService) {
        this.worksiteService = worksiteService;
    }

    @GetMapping
    public List<Worksite> getAllWorksites() {
        return worksiteService.getAllWorksites();
    }

    @GetMapping("/{id}")
    public Worksite getWorksite(@PathVariable Long id) {
        return worksiteService.getWorksiteById(id);
    }

    @PostMapping
    public Worksite addWorksite(@RequestBody Worksite worksite) {
        return worksiteService.addWorksite(worksite);
    }

    @PutMapping("/{id}")
    public Worksite updateWorksite(
            @PathVariable Long id,
            @RequestBody Worksite worksite) {

        return worksiteService.updateWorksite(id, worksite);
    }

    @DeleteMapping("/{id}")
    public void deleteWorksite(@PathVariable Long id) {
        worksiteService.deleteWorksite(id);
    }
}