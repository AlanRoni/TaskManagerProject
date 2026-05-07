package com.taskmanager;

import com.taskmanager.model.Task;
import com.taskmanager.model.TaskDao;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        TaskDao dao = new TaskDao();
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        while (choice != 5) {
            System.out.println("\n--- Task Manager ---");
            System.out.println("1. View Tasks");
            System.out.println("2. Add Task");
            System.out.println("3. Remove Task");
            System.out.println("4. Mark Task as Done");
            System.out.println("5. Exit");
            System.out.print("Choose: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println(dao.viewtasks());
                    break;
                case 2:
                    System.out.print("Enter description: ");
                    String desc = sc.nextLine();
                    Task t = new Task();
                    t.setTask(desc);
                    System.out.println(dao.addTasks(t));
                    break;
                case 3:
                    System.out.print("Enter task index to remove: ");
                    int removeIndex = sc.nextInt();
                    System.out.println(dao.removeTask(removeIndex));
                    break;
                case 4:
                    System.out.print("Enter task index to mark done: ");
                    int markIndex = sc.nextInt();
                    System.out.println(dao.markTask(markIndex));
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option, try again.");
            }
        }
        sc.close();
    }
}