package ru.aston.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.aston.connect.HibernateConnect;
import ru.aston.dao.implementDAO.CRUDCustomImpl;
import ru.aston.models.Task;
import ru.aston.models.workers.Developer;
import ru.aston.models.workers.NonDeveloper;
import ru.aston.models.workers.Worker;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
            return session.createQuery("SELECT w FROM Worker w", Worker.class).getResultList();
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
            boolean isTrue = true;

            for(Task elTask : worker.getTasks()){
                if(elTask.getId() == taskId){
                    isTrue = false;
                    break;
                }
            }

            if(isTrue) {
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
                task.getWorkers().remove(worker);

                session.update(worker);
                session.update(task);
            }
            transaction.commit();
        }
    }

    public void chacngeWorkerType(Worker entity, HttpServletRequest req, HttpServletResponse resp){
        try (Session session = HibernateConnect.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            try {
                String newWorkerType = (entity.getWorkerType().equals("NonDeveloper")) ? "Developer" : "NonDeveloper";

                String updateQuery = "UPDATE Worker SET worker_type = :newWorkerType WHERE id = :workerId";
                Query updateQueryObj = session.createQuery(updateQuery);
                updateQueryObj.setParameter("newWorkerType", newWorkerType);
                updateQueryObj.setParameter("workerId", entity.getId());
                updateQueryObj.executeUpdate();

                String deleteColumnsQuery = "";
                if (newWorkerType.equals("Developer")) {
                    deleteColumnsQuery = "UPDATE Worker SET role = NULL, programming_language = :programmingLanguage WHERE id = :workerId";
                } else {
                    deleteColumnsQuery = "UPDATE Worker SET programming_language = NULL, role = :role WHERE id = :workerId";
                }

                Query deleteColumnsQueryObj = session.createQuery(deleteColumnsQuery);
                deleteColumnsQueryObj.setParameter("workerId", entity.getId());


                if (newWorkerType.equals("Developer")) {
                    Developer developer = new Developer(entity, req.getParameter("programmingLanguage"));
                    deleteColumnsQueryObj.setParameter("programmingLanguage", developer.getProgrammingLanguage());
                } else {
                    NonDeveloper nonDeveloper = new NonDeveloper(entity, req.getParameter("role"));
                    deleteColumnsQueryObj.setParameter("role", nonDeveloper.getRole());
                }

                deleteColumnsQueryObj.executeUpdate();

                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }
}
