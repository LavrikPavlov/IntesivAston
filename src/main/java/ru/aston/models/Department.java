package ru.aston.models;

import lombok.Data;
import ru.aston.models.abstractModels.AbstractWorker;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "department")
public class Department {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name_depart", length = 30)
    private String nameDepart;

    @OneToMany(mappedBy = "department")
    private List<AbstractWorker> workers;
}
