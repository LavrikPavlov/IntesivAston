package ru.aston.dao;

import org.hibernate.Session;
import ru.aston.connect.HibernateConnect;
import ru.aston.dao.implementDAO.CRUDCustom;
import ru.aston.models.Department;

import java.util.List;

public class DepartmentDAOImpl implements CRUDCustom<Department> {

    @Override
    public Department findById(int id) {
        try (Session session = HibernateConnect.getSessionFactory().openSession()) {
            return session.get(Department.class, id);
        }
    }

    @Override
    public List<Department> findAll() {
        try (Session session = HibernateConnect.getSessionFactory().openSession()) {
            return session.createQuery("SELECT d FROM Department d", Department.class).getResultList();
        }
    }

    @Override
    public void save(Department entity) {
        try (Session session = HibernateConnect.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Department entity) {
        try (Session session = HibernateConnect.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Department entity) {
        try (Session session = HibernateConnect.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
        }
    }
}
