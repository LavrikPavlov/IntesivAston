package ru.aston.connect;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.aston.models.Department;
import ru.aston.models.Task;
import ru.aston.models.workers.Developer;
import ru.aston.models.workers.NonDeveloper;
import ru.aston.models.workers.Worker;

public class HibernateConnect {
    private static SessionFactory sessionFactory;

    public static void buildSessionFactory() {
        sessionFactory = new Configuration().configure()
                .buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            buildSessionFactory();
        }
        return sessionFactory;
    }


    public static void close() {
        getSessionFactory().close();
    }
}

