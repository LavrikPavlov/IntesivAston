package ru.aston.models.workers;

import lombok.Data;
import ru.aston.models.abstractModels.Worker;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Size;

@Data
@Entity
public class Developer extends Worker {

    @Column(name = "programming_language")
    @Size(max = 30)
    private String programmingLanguage;

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }
}