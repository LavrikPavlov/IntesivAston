package ru.aston.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.aston.connect.HibernateConnect;
import ru.aston.dao.implementDAO.CRUDCustomImpl;
import ru.aston.models.Task;
import ru.aston.models.workers.Worker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class WorkerDAO implements CRUDCustomImpl<Worker> {


    @Override
    public Worker findById(int id) {
        try(Session session = HibernateConnect.getSessionFactory().openSession()){
            return session.get(Worker.class, id);
        }
    }

    @Override
    public List<Worker> findAll() {
        try(Session session = HibernateConnect.getSessionFactory().openSession()){
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Worker> query = criteriaBuilder.createQuery(Worker.class);
            Root<Worker> root = query.from(Worker.class);
            query.select(root);
            return session.createQuery(query).list();
        }
    }

    @Override
    public void save(Worker entity) {
        try(Session session = HibernateConnect.getSessionFactory().openSession()){
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Worker entity) {
        try (Session session = HibernateConnect.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Worker entity) {
        try (Session session = HibernateConnect.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
        }
    }

    public void assignTask(int workerId, int taskId) {
        try (Session session = HibernateConnect.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Worker worker = session.get(Worker.class, workerId);
            Task task = session.get(Task.class, taskId);

            if (worker != null && task != null) {
                worker.getTasks().add(task);
                task.getWorkers().add(worker);
                session.update(worker);
                session.update(task);
            }

            transaction.commit();
        }
    }


    public void deleteTask(int workerId, int taskId) {
        try (Session session = HibernateConnect.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Worker worker = session.get(Worker.class, workerId);
            Task task = session.get(Task.class, taskId);

            if (worker != null && task != null) {
                worker.getTasks().remove(task);
                session.update(worker);
            }
            transaction.commit();
        }
    }
}
