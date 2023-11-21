package ru.aston.models.workers;

import ru.aston.models.Department;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("NonDeveloper")
public class NonDeveloper extends Worker {

    public NonDeveloper() {
        super();
    }

    public NonDeveloper(String login, String nameWorker,
                        Department department, String role) {
        super(login, nameWorker, department);
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
