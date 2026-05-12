package com.taskmanager.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.taskmanager.util.HibernateUtil;

public class TaskDao {

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
        List<Task> tasks = session.createQuery("FROM Tasks", Task.class).list();
        session.close();
        return tasks;
    }

    @SuppressWarnings("deprecation")
    public String removeTask(int i) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createMutationQuery("Delete from Tasks where id = :id").setParameter("id", i).executeUpdate();
            transaction.commit();
        }
        return "Task Removed";
    }

    public String markTask(int i) {
        tasks.get(i).setStatus(true);
        return "Task marked as done";
    }

    public String updateTask(int i, String desc) {
        tasks.get(i).setTask(desc);
        return "Task " + i + " updated";
    }
}
