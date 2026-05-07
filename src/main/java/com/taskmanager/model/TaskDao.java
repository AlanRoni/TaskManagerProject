package com.taskmanager.model;

import java.util.ArrayList;
import java.util.List;

public class TaskDao {

    List<Task> tasks = new ArrayList<>();
    public List<Task> viewtasks(){
        return tasks;
    }
    public String addTasks(Task t){
        tasks.add(t);
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
