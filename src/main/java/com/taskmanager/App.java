package com.taskmanager;

import java.util.Scanner;

import com.taskmanager.model.Task;
import com.taskmanager.model.TaskDao;
import com.taskmanager.util.HibernateUtil;

public class App {
    public static void main(String[] args) {
        TaskDao dao = new TaskDao();
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        while (choice != 6) {
            System.out.println("\n--- Task Manager ---");
            System.out.println("1. View Tasks");
            System.out.println("2. Add Task");
            System.out.println("3. Remove Task");
            System.out.println("4. Mark Task as Done");
            System.out.println("5. Update Task");
            System.out.println("6. Exit");
            System.out.print("Choose: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> System.out.println(dao.viewTasks());
                case 2 -> {
                    System.out.print("Enter description: ");
                    String desc = sc.nextLine();
                    Task t = new Task();
                    t.setTask(desc);
                    System.out.println(dao.addTasks(t));
                }
                case 3 -> {
                    System.out.print("Enter task id to remove: ");
                    int removeId = sc.nextInt();
                    System.out.println(dao.removeTask(removeId));
                }
                case 4 -> {
                    System.out.print("Enter task id to mark done: ");
                    int markId = sc.nextInt();
                    System.out.println(dao.markTask(markId));
                }
                case 5 -> {
                    System.out.print("Enter task id to update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter new description: ");
                    String newDesc = sc.nextLine();
                    System.out.println(dao.updateTask(updateId, newDesc));
                }
                case 6 -> {
                    System.out.println("Goodbye!");
                    HibernateUtil.endSession();
                }
                default -> System.out.println("Invalid option, try again.");
            }
        }
        sc.close();
    }
}