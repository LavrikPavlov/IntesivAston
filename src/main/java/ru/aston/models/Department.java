package ru.aston.models;

import java.util.List;

public class Department {

    private int id;

    private String nameDepart;

    private List<Worker> workers;

    public Department() {

    }

    public Department(int id, String nameDepart, List<Worker> workers) {
        this.id = id;
        this.nameDepart = nameDepart;
        this.workers = workers;
    }

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

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", nameDepart='" + nameDepart + '\'' +
                ", workers=" + workers +
                '}';
    }
}
