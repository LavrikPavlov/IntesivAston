package ru.aston.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.stereotype.Service;
import ru.aston.models.workers.Developer;
import ru.aston.models.workers.NonDeveloper;
import ru.aston.models.workers.Worker;
import ru.aston.repositories.DeveloperRepository;
import ru.aston.repositories.NonDeveloperRepository;
import ru.aston.repositories.TaskRepository;
import ru.aston.repositories.WorkerRepository;
import ru.aston.util.WorkerUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Transactional
@Service
public class WorkerService {

    @Autowired
    private EntityManager entityManager;

    private final WorkerRepository workerRepository;

    private final TaskRepository taskRepository;

    private final DeveloperRepository developerRepository;

    private final NonDeveloperRepository nonDeveloperRepository;

    @Autowired
    public WorkerService(WorkerRepository workerRepository, TaskRepository taskRepository,
                         DeveloperRepository developerRepository, NonDeveloperRepository nonDeveloperRepository) {
        this.workerRepository = workerRepository;
        this.taskRepository = taskRepository;
        this.developerRepository = developerRepository;
        this.nonDeveloperRepository = nonDeveloperRepository;
    }

    public List<Worker> findAll() {
        return workerRepository.findAll();
    }

    public Worker findById(int id) {
        Optional<Worker> worker = workerRepository.findById(id);
        return worker.orElse(null);
    }

    @Transactional
    public void save(Worker worker) {
        String workerType =
                WorkerUtil.determineWorkerTypeByDepartmentId(worker.getDepartment().getId());
        Worker newWorker = WorkerUtil.createWorkerByType(workerType, worker);
        workerRepository.save(newWorker);

        if (newWorker instanceof Developer) {
            developerRepository.save((Developer) newWorker);
        } else if (newWorker instanceof NonDeveloper) {
            nonDeveloperRepository.save((NonDeveloper) newWorker);
        }

    }


    @Transactional
    public void updateDev(int id, Developer updateWorker) {
        updateWorker.setId(id);
        developerRepository.save(updateWorker);
    }

    @Transactional
    public void updateNon(int id, NonDeveloper updateWorker) {
        updateWorker.setId(id);
        nonDeveloperRepository.save(updateWorker);
    }


    @Transactional
    public void delete(int id) {
        workerRepository.deleteById(id);
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


    public Optional<Worker> getPersonByLogin(String login) {
        return workerRepository.findByLogin(login);
    }

}
