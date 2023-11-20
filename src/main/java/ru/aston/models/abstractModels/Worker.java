package ru.aston.models.abstractModels;

import lombok.Data;
import ru.aston.models.Department;
import ru.aston.models.Task;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "login", unique = true)
    @Size(max = 20)
    private String login;

    @Column(name = "name_user")
    @Size(max = 30)
    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToMany(mappedBy = "workers")
    private List<Task> tasks;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Worker worker = (Worker) o;
        return id == worker.id &&
                Objects.equals(login, worker.login) &&
                Objects.equals(name, worker.name) &&
                Objects.equals(department, worker.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, name, department);
    }
}
