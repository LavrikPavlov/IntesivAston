package ru.aston.util;

import ru.aston.models.workers.Worker;
import ru.aston.models.workers.Developer;
import ru.aston.models.workers.NonDeveloper;

public class WorkerUtil {

    public static Worker createWorkerByType(String workerType, Worker originalWorker) {
        switch (workerType) {
            case "Developer":
                return new Developer(originalWorker, "None");
            case "NonDeveloper":
                return new NonDeveloper(originalWorker, "None");
            default:
                throw new IllegalArgumentException("Не известный тип работника: " + workerType);
        }
    }

    public static String determineWorkerTypeByDepartmentId(int departmentId) {
        return (departmentId  == 1) ?  "Developer" : "NonDeveloper";
    }
}
