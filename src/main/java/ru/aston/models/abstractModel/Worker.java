package ru.aston.models.abstractModel;

import ru.aston.models.Department;
import ru.aston.models.Task;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "worker_type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "Worker")
public abstract class Worker extends AbstractWorkerFields{

    @Column(name = "login", unique = true)
    @Size(max = 30, min = 4, message = "Логин слишком короткий/длинный")
    @NotEmpty(message = "Поле логина не должно быть пустым.")
    private String login;

    @Column(name = "name_user")
    @Size(max = 30, min = 2, message = "Имя слишком короткий/длинный")
    @NotEmpty(message = "Поле имени не должно быть пустым.")
    private String nameWorker;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "worker_type", insertable = false, updatable = false)
    private String workerType;

    @ManyToMany(mappedBy = "workers", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    List<Task> tasks;

    public Worker() {
    }

    public Worker(String login, String nameWorker, Department department, String workerType, List<Task> tasks) {
        this.login = login;
        this.nameWorker = nameWorker;
        this.department = department;
        this.workerType = workerType;
        this.tasks = tasks;
    }



    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNameWorker() {
        return nameWorker;
    }

    public void setNameWorker(String nameWorker) {
        this.nameWorker = nameWorker;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getWorkerType() {
        return workerType;
    }

    public void setWorkerType(String workerType) {
        this.workerType = workerType;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
