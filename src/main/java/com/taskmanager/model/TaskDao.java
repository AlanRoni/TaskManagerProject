package com.taskmanager.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.taskmanager.util.HibernateUtil;


public class TaskDao {

    

    List<Task> tasks = new ArrayList<>();
    
    public String addTasks(Task t){
        try{
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(t);
        transaction.commit();
        session.close();
        }
        catch(Exception e){
            return e.getMessage();
        }
        return "Task added";
    }


    public String removeTask(int i){
        tasks.remove(i);
        return "Task removed";
    }
    public String markTask(int i){
        tasks.get(i).setStatus(true);
        return "Task marked as done";
    }
    public String updateTask(int i, String desc){
        tasks.get(i).setTask(desc);
        return "Task " + i + " updated";
    }
}
