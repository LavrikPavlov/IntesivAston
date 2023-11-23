package ru.aston.models.workers;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Size;

@Entity
@DiscriminatorValue("NonDeveloper")
public class NonDeveloper extends Worker {

    public NonDeveloper() {
        super();
    }

    public NonDeveloper(Worker worker, String role) {
        super(worker.getLogin(), worker.getNameWorker(), worker.getDepartment(), worker.getWorkerType(), worker.getTasks());
        this.role = role;
    }

    @Column(name = "role")
    @Size(min = 2, max = 30, message = "Роль в компании слишком короткая/длинная")
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
