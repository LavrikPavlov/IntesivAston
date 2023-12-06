package ru.aston.models.workers;

import ru.aston.models.Department;
import ru.aston.models.Task;
import ru.aston.models.abstractModels.AbstractWorkerFields;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "Worker")
@DiscriminatorColumn(name = "worker_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Worker extends AbstractWorkerFields {

    public Worker() {
    }

    public Worker(String login, String nameWorker, Department department) {
        this.login = login;
        this.nameWorker = nameWorker;
        this.department = department;
    }

    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "name_user")
    private String nameWorker;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "worker_type", insertable = false, updatable = false)
    private String workerType;

    @ManyToMany(mappedBy = "workers", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    List<Task> tasks;



    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNameWorker() {
        return nameWorker;
    }

    public void setNameUser(String nameUser) {
        this.nameWorker = nameUser;
    }

    public void setNameWorker(String nameWorker) {
        this.nameWorker = nameWorker;
    }

    public String getWorkerType() {
        return workerType;
    }

    public void setWorkerType(String workerType) {
        this.workerType = workerType;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
