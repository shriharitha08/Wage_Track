package com.example.wagetrack.service;

import com.example.wagetrack.model.Worker;
import com.example.wagetrack.repository.WorkerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerService {

    private final WorkerRepository workerRepository;

    public WorkerService(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    public List<Worker> getAllWorkers() {
        return workerRepository.findAll();
    }

    public Worker getWorkerById(Long id) {
        return workerRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Worker not found with id: " + id));
    }

    public Worker addWorker(Worker worker) {
        return workerRepository.save(worker);
    }

    public Worker updateWorker(Long id, Worker updatedWorker) {

        Worker worker = getWorkerById(id);

        worker.setName(updatedWorker.getName());
        worker.setPhone(updatedWorker.getPhone());
        worker.setDailyWage(updatedWorker.getDailyWage());
        worker.setStandardWorkHours(
                updatedWorker.getStandardWorkHours());
        worker.setStatus(updatedWorker.getStatus());

        return workerRepository.save(worker);
    }

    public void deleteWorker(Long id) {

        Worker worker = getWorkerById(id);

        workerRepository.delete(worker);
    }
}