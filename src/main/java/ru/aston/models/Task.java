package ru.aston.models;

import ru.aston.models.workers.Worker;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name_task")
    private String nameTask;

    @Column(name = "text_task")
    private String textTask;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "worker_task",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "worker_id")
    )
    private List<Worker> workers;

    public Task() {

    }

    public int getId() {
        return id;
    }

    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }

    public String getTextTask() {
        return textTask;
    }

    public void setTextTask(String textTask) {
        this.textTask = textTask;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }


}
