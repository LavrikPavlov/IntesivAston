package ru.aston.dao.workersDAO;

import org.hibernate.Session;
import ru.aston.connect.HibernateConnect;
import ru.aston.dao.abstractDAO.GenericWorkerDAO;
import ru.aston.models.workers.Developer;

public class DeveloperDAO extends GenericWorkerDAO<Developer> {

    public DeveloperDAO(Class<Developer> newClass) {
        super(newClass);
    }

    public void setProgrammingLanguage(Developer developer, String programmingLanguage) {
        Session session = HibernateConnect.getSessionFactory().openSession();
        session.beginTransaction();

        try {
            Developer existingDeveloper = session.get(Developer.class, developer.getId());
            if (existingDeveloper != null) {
                existingDeveloper.setProgrammingLanguage(programmingLanguage);
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
