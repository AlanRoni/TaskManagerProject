package com.taskmanager.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.taskmanager.util.HibernateUtil;

public class TaskDao {

    @SuppressWarnings("UseSpecificCatch")
    public String addTasks(Task t) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.persist(t);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Task added";
    }

    public List<Task> viewTasks() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Task> tasks = session.createQuery("FROM Task", Task.class).list();
        session.close();
        return tasks;
    }

    @SuppressWarnings("deprecation")
    public String removeTask(int i) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createMutationQuery("Delete from Task where id = :id").setParameter("id", i).executeUpdate();
            transaction.commit();
        }
        return "Task Removed";
    }

    public String markTask(int i) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Task t = (session.find(Task.class, i));
        t.setStatus(true);
        session.merge(t);
        transaction.commit();
        session.close();
        return "Marked as done";
}

    public String updateTask(int i, String desc) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Task t = session.find(Task.class, i);
        t.setTask(desc);
        session.merge(t);
        transaction.commit();
        session.close();
        return "Task Updated";
    }
}
