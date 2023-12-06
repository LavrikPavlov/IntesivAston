package ru.aston.models.workers;


import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Developer")
public class Developer extends Worker {

    @Column(name = "programming_language", length = 30)
    private String programmingLanguage;

    public Developer() {
        super();
    }

    public Developer(Worker worker, String programmingLanguage) {
        super(worker.getLogin(), worker.getNameWorker(), worker.getDepartment());
        this.programmingLanguage = programmingLanguage;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }
}