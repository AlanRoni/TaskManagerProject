package com.taskmanager.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@SuppressWarnings("UseSpecificCatch")
public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void endSession() {
        if (sessionFactory != null) {
            getSessionFactory().close();
        }
    }
}
