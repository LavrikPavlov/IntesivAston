package ru.aston.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aston.models.abstractModel.Worker;
import ru.aston.models.workers.Developer;
import ru.aston.models.workers.NonDeveloper;
import ru.aston.repositories.WorkerRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Transactional
@Service
public class WorkerService {

    private final WorkerRepository workerRepository;

    @Autowired
    public WorkerService(WorkerRepository workerRepository){
        this.workerRepository = workerRepository;
    }

    public List<Worker> findAll() {
        return workerRepository.findAll();
    }

    public Worker findById(int id){
        Optional<Worker> worker = workerRepository.findById(id);
        return worker.orElse(null);
    }

    @Transactional
    public void save(Worker entity){
        workerRepository.save(entity);
    }

    public void update(int id, Worker updateWorker){
        updateWorker.setId(id);
        workerRepository.save(updateWorker);
    }

    @Transactional
    public void delete(int id){
        workerRepository.deleteById(id);
    }

    @Transactional
    public void changeType(Worker worker, String newType) {
        if (worker.getWorkerType().equals(newType)) {
            return;
        }

        Worker newWorker = null;
        switch (newType) {
            case "Developer":
                newWorker = new Developer(worker, "defaultProgrammingLanguage");
                break;
            case "NonDeveloper":
                newWorker = new NonDeveloper(worker, "defaultRole");
                break;

        }

        if (newWorker != null) {
            workerRepository.save(newWorker);
            workerRepository.delete(worker);
        }
    }

}
