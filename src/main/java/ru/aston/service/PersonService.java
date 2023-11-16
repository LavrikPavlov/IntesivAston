package ru.aston.service;

import ru.aston.dao.PersonDAO;

public class PersonService {

    private PersonDAO personDAO;

    public PersonService(){
        this.personDAO = new PersonDAO();
    }
}
