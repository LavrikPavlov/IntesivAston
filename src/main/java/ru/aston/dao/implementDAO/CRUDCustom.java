package ru.aston.dao.implementDAO;

import java.util.List;

public interface CRUDCustom<T> {

    T findById(int id);

    List<T> findAll();

    void save(T entity);

    void update(T entity);

    void delete(T entity);
}
