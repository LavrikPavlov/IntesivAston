package ru.aston.models.workers;

import lombok.Data;
import ru.aston.models.abstractModels.Worker;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
public class NonDeveloper extends Worker {
    @Column(name = "role")
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
