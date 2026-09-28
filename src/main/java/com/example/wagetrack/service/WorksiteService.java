package com.example.wagetrack.service;

import com.example.wagetrack.model.Worksite;
import com.example.wagetrack.repository.WorksiteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorksiteService {

    private final WorksiteRepository worksiteRepository;

    public WorksiteService(WorksiteRepository worksiteRepository) {
        this.worksiteRepository = worksiteRepository;
    }

    public List<Worksite> getAllWorksites() {
        return worksiteRepository.findAll();
    }

    public Worksite getWorksiteById(Long id) {
        return worksiteRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Worksite not found"));
    }

    public Worksite addWorksite(Worksite worksite) {
        return worksiteRepository.save(worksite);
    }

    public Worksite updateWorksite(
            Long id,
            Worksite updatedWorksite) {

        Worksite worksite = getWorksiteById(id);

        worksite.setSiteName(updatedWorksite.getSiteName());
        worksite.setLocation(updatedWorksite.getLocation());
        worksite.setDescription(updatedWorksite.getDescription());

        return worksiteRepository.save(worksite);
    }

    public void deleteWorksite(Long id) {
        worksiteRepository.deleteById(id);
    }
}