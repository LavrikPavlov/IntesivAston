package ru.aston.models;

import lombok.Data;
import ru.aston.models.abstractModels.Worker;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@Table(name = "Department")
public class Department {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name_depart")
    @Size(max = 30)
    private String nameDepart;

    @OneToMany(mappedBy = "department")
    private List<Worker> workers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameDepart() {
        return nameDepart;
    }

    public void setNameDepart(String nameDepart) {
        this.nameDepart = nameDepart;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }
}
