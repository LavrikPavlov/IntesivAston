package ru.aston.models;

import java.util.List;

public class Task {

    private int id;

    private String nameTask;

    private String taskText;

    private List<Worker> workers;

    public Task(){

    }

    public Task(int id, String nameTask, String taskText, List<Worker> workers) {
        this.id = id;
        this.nameTask = nameTask;
        this.taskText = taskText;
        this.workers = workers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }

    public String getTaskText() {
        return taskText;
    }

    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", nameTask='" + nameTask + '\'' +
                ", taskText='" + taskText + '\'' +
                '}';
    }
}
