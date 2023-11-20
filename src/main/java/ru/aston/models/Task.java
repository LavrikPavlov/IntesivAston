package ru.aston.models;

import lombok.Data;
import ru.aston.models.abstractModels.AbstractWorker;

import javax.persistence.*;
import java.util.List;

@Data
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
    private List<AbstractWorker> workers;
}
