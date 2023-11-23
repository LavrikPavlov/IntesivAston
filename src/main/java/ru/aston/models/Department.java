package ru.aston.models;

import ru.aston.models.abstractModel.Worker;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name_depart", length = 30)
    @Size(min = 2, max = 30, message = "Короткое название отдела")
    private String nameDepart;

    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
    private List<Worker> workers;


    public Department(){

    }

    public int getId() {
        return id;
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