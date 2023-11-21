package ru.aston.dao;

import org.hibernate.Session;
import ru.aston.connect.HibernateConnect;
import ru.aston.dao.implementDAO.CRUDCustomImpl;
import ru.aston.models.Task;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class TaskDAO implements CRUDCustomImpl<Task> {


    @Override
    public Task findById(int id) {
        try (Session session = HibernateConnect.getSessionFactory().openSession()) {
            return session.get(Task.class, id);
        }
    }

    @Override
    public List<Task> findAll() {
        try (Session session = HibernateConnect.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Task> query = criteriaBuilder.createQuery(Task.class);
            Root<Task> root = query.from(Task.class);
            query.select(root);
            return session.createQuery(query).list();
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
