package ru.aston.models;

import java.util.List;

public class Worker {

    private int id;

    private String login;

    private String nameWorker;

    private Department department;

    private List<Task> tasks;

    public Worker(){

    }

    public Worker(int id, String login, String nameWorker, Department department, List<Task> tasks) {
        this.id = id;
        this.login = login;
        this.nameWorker = nameWorker;
        this.department = department;
        this.tasks = tasks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNameWorker() {
        return nameWorker;
    }

    public void setNameWorker(String nameWorker) {
        this.nameWorker = nameWorker;
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
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", nameWorker='" + nameWorker + '\'' +
                ", department=" + department +
                '}';
    }
}
