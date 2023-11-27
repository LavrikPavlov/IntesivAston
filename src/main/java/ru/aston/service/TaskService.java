package ru.aston.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aston.models.Task;
import ru.aston.models.workers.Worker;
import ru.aston.repositories.TaskRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Transactional
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public List<Task> findAll(){
        return taskRepository.findAll();
    }

    public Task findOne(int id){
        Optional<Task> task = taskRepository.findById(id);
        return task.orElse(null);
    }

    @Transactional
    public void save(Task task){
        taskRepository.save(task);
    }

    @Transactional
    public void update(int id, Task updateTask){
        updateTask.setId(id);
        taskRepository.save(updateTask);
    }

    @Transactional
    public void delete(int id){
        taskRepository.deleteById(id);
    }

    @Transactional
    public void assignTask(int id, Worker worker){
        taskRepository.findById(id).ifPresent(task -> {
            task.getWorkers().add(worker);
            worker.getTasks().add(task);
        });
    }

    @Transactional
    public void deleteTask(int id, Worker worker){
        taskRepository.findById(id).ifPresent(task -> {
            task.getWorkers().remove(worker);
            worker.getTasks().remove(task);
        });
    }

}
