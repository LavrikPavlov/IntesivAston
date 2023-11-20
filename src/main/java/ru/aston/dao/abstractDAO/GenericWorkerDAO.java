package ru.aston.dao.abstractDAO;


import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.aston.connect.HibernateConnect;
import ru.aston.dao.interfaceCRUD.CRUDCustomDAO;
import ru.aston.models.Task;
import ru.aston.models.abstractModels.Worker;

import java.util.List;

public abstract class GenericWorkerDAO<T> implements CRUDCustomDAO<T> {

    private final Class<T> newClass;

    public GenericWorkerDAO(Class<T> newClass) {
        this.newClass = newClass;
    }

    @Override
    public T findById(long id) {
        try (Session session = HibernateConnect.getSessionFactory().openSession()) {
            return session.get(newClass, id);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка в findById", e);
        }
    }

    @Override
    public List<T> findAll() {
        try (Session session = HibernateConnect.getSessionFactory().openSession()) {
            return session.createQuery("FROM " + newClass.getSimpleName(), newClass).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка в findAll", e);
        }
    }

    @Override
    public void save(T entity) {
        try (Session session = HibernateConnect.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка в save", e);
        }
    }

    @Override
    public void update(T entity) {
        try (Session session = HibernateConnect.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка в update", e);
        }
    }

    @Override
    public void delete(T entity) {
        try (Session session = HibernateConnect.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка в delete", e);
        }
    }

    public void assignTaskToWorker(int workerId, int taskId) {
        try (Session session = HibernateConnect.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Worker worker = session.get(Worker.class, workerId);
            Task task = session.get(Task.class, taskId);

            if (worker != null && task != null) {
                worker.getTasks().add(task);
                session.update(worker);
            }

            transaction.commit();
        } catch (Exception e) {
            throw new RuntimeException("Error assigning task to worker", e);
        }
    }

    public void removeTaskFromWorker(int workerId, int taskId) {
        try (Session session = HibernateConnect.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Worker worker = session.get(Worker.class, workerId);
            Task task = session.get(Task.class, taskId);

            if (worker != null && task != null) {
                worker.getTasks().remove(task);
                session.update(worker);
            }

            transaction.commit();
        } catch (Exception e) {
            throw new RuntimeException("Error removing task from worker", e);
        }
    }

}
