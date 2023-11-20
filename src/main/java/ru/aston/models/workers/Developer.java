package ru.aston.models.workers;


import ru.aston.models.Department;
import ru.aston.models.abstractModels.AbstractWorker;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Developer")
public class Developer extends AbstractWorker {

    public Developer() {
    }

    public Developer(String login, String nameUser, Department department, String programmingLanguage) {
        super(login, nameUser, department);
        this.programmingLanguage = programmingLanguage;
    }

    @Column(name = "programming_language", length = 30)
    private String programmingLanguage;

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }
}