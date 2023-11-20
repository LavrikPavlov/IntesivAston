package ru.aston.dao.workersDAO;

import org.hibernate.Session;
import ru.aston.connect.HibernateConnect;
import ru.aston.dao.abstractDAO.GenericWorkerDAO;
import ru.aston.models.workers.NonDeveloper;

public class NonDeveloperDAO extends GenericWorkerDAO<NonDeveloper> {

    public NonDeveloperDAO(Class<NonDeveloper> newClass) {
        super(newClass);
    }

    public void setRole(NonDeveloper nonDeveloper, String role) {
        Session session = HibernateConnect.getSessionFactory().openSession();
        session.beginTransaction();

        try {
            NonDeveloper existingDeveloper = session.get(NonDeveloper.class, nonDeveloper.getId());
            if (existingDeveloper != null) {
                existingDeveloper.setRole(role);
                session.update(existingDeveloper);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
