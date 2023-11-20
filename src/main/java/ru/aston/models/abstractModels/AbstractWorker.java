package ru.aston.models.abstractModels;

import ru.aston.models.Department;

import javax.persistence.*;


@Entity
@Table(name = "Worker")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractWorker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "name_user")
    private String nameUser;

    public AbstractWorker() {

    }

    public AbstractWorker(String login, String nameUser, Department department) {
        this.login = login;
        this.nameUser = nameUser;
        this.department = department;
    }

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
