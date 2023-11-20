package ru.aston.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ru.aston.connect.HibernateConnect;
import ru.aston.dao.interfaceCRUD.CRUDCustomDAO;
import ru.aston.models.Task;
import ru.aston.models.abstractModels.Worker;

import java.util.List;

public class TaskDAO implements CRUDCustomDAO<Task> {

    public TaskDAO() {
    }

    @Override
    public Task findById(long id) {
        Task task;
        try (Session session = HibernateConnect.getSessionFactory().openSession()) {
            task = session.get(Task.class, (int) id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return task;
    }

    @Override
    public List<Task> findAll() {
        List<Task> tasks;
        try (Session session = HibernateConnect.getSessionFactory().openSession()) {
            tasks = session.createQuery("FROM Task", Task.class).list();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return tasks;
    }

    @Override
    public void save(Task entity) {
        try (Session session = HibernateConnect.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Task entity) {
        try (Session session = HibernateConnect.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Task entity) {
        try (Session session = HibernateConnect.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Task task = session.get(Task.class, entity.getId());
            if (task != null) {
                session.delete(task);
            }
            transaction.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Worker> getWorkersByTaskId(int idTask) {
        List<Worker> workers;
        try (Session session = HibernateConnect.getSessionFactory().openSession()) {
            workers = session.createQuery(
                            "SELECT w FROM Worker w JOIN w.tasks t WHERE t.id = :taskId", Worker.class)
                    .setParameter("taskId", idTask)
                    .list();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return workers;
    }
}
