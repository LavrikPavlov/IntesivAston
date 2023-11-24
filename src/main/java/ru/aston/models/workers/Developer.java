package ru.aston.models.workers;

import ru.aston.dto.WorkerDTO;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Size;


@Entity
@DiscriminatorValue("Developer")
public class Developer extends Worker {

    @Column(name = "programming_language")
    @Size(min = 1, max = 30, message = "Язык слищком длинный/короткий" )
    private String programmingLanguage;

    public Developer() {
        super();
    }

    public Developer(Worker worker, String programmingLanguage) {
        super(worker.getLogin(), worker.getNameWorker(), worker.getDepartment(), worker.getWorkerType(), worker.getTasks());
        this.programmingLanguage = programmingLanguage;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }
}


