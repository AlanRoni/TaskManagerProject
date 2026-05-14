package com.taskmanager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "Task")
@Entity
public class Task {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;
    String description;
    boolean status = false;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTask() {
        return description;
    }
    public void setTask(String task) {
        this.description = task;
    }
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Task{");
        sb.append("id=").append(id);
        sb.append(", task=").append(description);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
    
    public Task(){}
}
