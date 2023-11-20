package ru.aston.dao.interfaceCRUD;

import java.util.List;

public interface CRUDCustomDAO<T> {

    T findById(long id);

    List<T> findAll();

    void save(T entity);

    void update(T entity);

    void delete(T entity);

}
