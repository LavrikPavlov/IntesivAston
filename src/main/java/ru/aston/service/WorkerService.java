package ru.aston.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aston.models.workers.Worker;
import ru.aston.models.workers.Developer;
import ru.aston.models.workers.NonDeveloper;
import ru.aston.repositories.TaskRepository;
import ru.aston.repositories.WorkerRepository;
import ru.aston.util.WorkerUtil;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Transactional
@Service
public class WorkerService {

    private final WorkerRepository workerRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public WorkerService(WorkerRepository workerRepository, TaskRepository taskRepository){
        this.workerRepository = workerRepository;
        this.taskRepository = taskRepository;
    }

    public List<Worker> findAll() {
        return workerRepository.findAll();
    }

    public Worker findById(int id){
        Optional<Worker> worker = workerRepository.findById(id);
        return worker.orElse(null);
    }

    @Transactional
    public void save(Worker worker) {
        String workerType =
                WorkerUtil.determineWorkerTypeByDepartmentId(worker.getDepartment().getId());
        Worker newWorker = WorkerUtil.createWorkerByType(workerType, worker);
        workerRepository.save(newWorker);
    }


    @Transactional
    public void update(int id, Worker updateWorker){
        updateWorker.setId(id);
        workerRepository.save(changeType(updateWorker, updateWorker.getWorkerType()));
    }


    @Transactional
    public void delete(int id){
        workerRepository.deleteById(id);
    }


    @Transactional
    private Worker changeType(Worker worker, String newType) {
        if (!worker.getWorkerType().equals(newType)) {
            Worker newWorker = null;
            if (newType.equals("Developer")){
                return new Developer();
            }else{
                return new NonDeveloper();
            }
        }
        return worker;
    }

    @Transactional
    public void assignTask(int workerId, int taskId) {
        workerRepository.findById(workerId).ifPresent(worker -> {
            taskRepository.findById(taskId).ifPresent(task -> {
                if (!worker.getTasks().contains(task)) {
                    worker.getTasks().add(task);
                    task.getWorkers().add(worker);
                    workerRepository.save(worker);
                    taskRepository.save(task);
                }
            });
        });
    }

    @Transactional
    public void deleteTask(int workerId, int taskId) {
        workerRepository.findById(workerId).ifPresent(worker -> {
            taskRepository.findById(taskId).ifPresent(task -> {
                if (worker.getTasks().contains(task)) {
                    worker.getTasks().remove(task);
                    task.getWorkers().remove(worker);
                    workerRepository.save(worker);
                    taskRepository.save(task);
                }
            });
        });
    }


    public Optional<Worker> getPersonByLogin(String login){
        return workerRepository.findByLogin(login);
    }

}
