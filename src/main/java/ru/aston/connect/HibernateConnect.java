package ru.aston.connect;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConnect {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.properties");
            return configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Ошибка в постройки hibernate factory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void close() {
        getSessionFactory().close();
    }
}
