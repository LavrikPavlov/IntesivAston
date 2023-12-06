package ru.aston.dao;

import org.hibernate.Session;
import ru.aston.connect.HibernateConnect;
import ru.aston.dao.implementDAO.CRUDCustom;
import ru.aston.models.Task;

import java.util.List;

public class TaskDAOImpl implements CRUDCustom<Task> {


    @Override
    public Task findById(int id) {
        try (Session session = HibernateConnect.getSessionFactory().openSession()) {
            return session.get(Task.class, id);
        }
    }

    @Override
    public List<Task> findAll() {
        try (Session session = HibernateConnect.getSessionFactory().openSession()) {

            return session.createQuery("SELECT t FROM Task t", Task.class).getResultList();
        }
    }

    @Override
    public void save(Task entity) {
        try (Session session = HibernateConnect.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Task entity) {
        try (Session session = HibernateConnect.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Task entity) {
        try (Session session = HibernateConnect.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
        }
    }
}
