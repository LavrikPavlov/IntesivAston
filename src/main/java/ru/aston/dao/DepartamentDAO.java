package ru.aston.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.aston.connect.HibernateConnect;
import ru.aston.dao.interfaceCRUD.CRUDCustomDAO;
import ru.aston.models.Department;
import ru.aston.models.abstractModels.Worker;

import java.util.*;

public class DepartamentDAO implements CRUDCustomDAO<Department> {

    public DepartamentDAO() {
    }

    @Override
    public Department findById(long id) {
        Department department;
        try (Session session = HibernateConnect.getSessionFactory().openSession()) {
            department = session.get(Department.class, (int) id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return department;
    }

    @Override
    public List<Department> findAll() {
        List<Department> departmentList;
        try (Session session = HibernateConnect.getSessionFactory().openSession()) {
            departmentList = session.createQuery("FROM Department", Department.class).list();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return departmentList;
    }

    @Override
    public void save(Department entity) {
        try (Session session = HibernateConnect.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Department entity) {
        try (Session session = HibernateConnect.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Department entity) {
        try (Session session = HibernateConnect.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Department department = session.get(Department.class, entity.getId());
            if (department != null) {
                session.delete(department);
            }
            transaction.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Worker> getWorkersByDepartmentId(int departmentId) {
        List<Worker> workers = new ArrayList<>();
        try (Session session = HibernateConnect.getSessionFactory().openSession()) {
            workers = session.createQuery(
                            "SELECT w FROM Worker w JOIN w.department d WHERE d.id = :departmentId", Worker.class)
                    .setParameter("departmentId", departmentId)
                    .list();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return workers;
    }
}

