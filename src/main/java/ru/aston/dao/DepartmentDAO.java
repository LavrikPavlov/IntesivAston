package ru.aston.dao;

import org.hibernate.Session;
import ru.aston.connect.HibernateConnect;
import ru.aston.dao.implementDAO.CRUDCustomImpl;
import ru.aston.models.Department;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class DepartmentDAO implements CRUDCustomImpl<Department> {

    @Override
    public Department findById(int id) {
        try (Session session = HibernateConnect.getSessionFactory().openSession()) {
            return session.get(Department.class, id);
        }
    }

    @Override
    public List<Department> findAll() {
        try (Session session = HibernateConnect.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Department> query = criteriaBuilder.createQuery(Department.class);
            Root<Department> root = query.from(Department.class);
            query.select(root);
            return session.createQuery(query).list();
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
