package ru.aston.models.workers;


import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("NonDeveloper")
public class NonDeveloper extends Worker {

    public NonDeveloper() {
        super();
    }

    public NonDeveloper(Worker worker, String role) {
        super(worker.getLogin(), worker.getNameWorker(), worker.getDepartment());
        this.role = role;
    }

    @Column(name = "role")
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
