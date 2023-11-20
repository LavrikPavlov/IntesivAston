package ru.aston.models.workers;


import ru.aston.models.Department;
import ru.aston.models.abstractModels.AbstractWorker;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "NonDeveloper")
public class NonDeveloper extends AbstractWorker {

    public NonDeveloper() {

    }

    public NonDeveloper(String login, String nameUser, Department department, String role) {
        super(login, nameUser, department);
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
