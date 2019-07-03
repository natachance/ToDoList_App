package app;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TaskList {
    Scanner scanner = new Scanner(System.in);
    private ArrayList<Task> tasks = new ArrayList<>();
    private ArrayList<Task> completedTasks = new ArrayList<>();
    private ArrayList<Task> tasksToRemove = new ArrayList<>();

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    //getter and setter methods for above ArrayLists
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public ArrayList<Task> getCompletedTasks() {
        return completedTasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void setCompletedTasks(ArrayList<Task> completedTasks) {
        this.completedTasks = completedTasks;
    }

    //adding tasks to the TaskList ArrayList
    public void addingTasks() {
        System.out.println("Please enter your tasks, hitting enter after each one. " +
                "When you've finished, type x and hit enter.");

        boolean addingTasks = true;
        while (addingTasks) {
            Task task = new Task();
            String title = scanner.nextLine();

            if (title.equalsIgnoreCase("x")) {
                break;
            } else if (title.isEmpty()) {
                continue;
            } else {
                task.setTitle(title);
                tasks.add(task);
            }
        }
    }

    //selecting and printing a random task from the ArrayList of tasks
    public void presentTask() {
        while (checkForTasks() == false) {
            int value;
            Random r = new Random();
            value = r.nextInt((tasks.size() - 0) + 1);

            for (Task t : tasks) {
                 if (tasks.indexOf(t) == value) {
                     if (t.getTitle().isEmpty()){
                         continue;
                     } else {
                         System.out.println("Your task is: " + t.toString());
                         System.out.println();
                         return;
                     }
                }
            }
        }
        if(checkForTasks()){
            return;
        }
    }

    //marking a task complete
    public void completeTask() {
        if (checkForTasks()) {
            return;
        } else {
            System.out.println("Please type in the number of the task you would like to mark complete and hit enter.");

            //printing all tasks to allow user selection
            for (Task t : tasks) {
                System.out.println((tasks.indexOf(t) + 1) + ". " + t.toString());
            }

            // marking a task complete by moving to the completedTasks ArrayList
            int selection = scanner.nextInt();

            for (Task t : tasks) {
                if ((tasks.indexOf(t) + 1) == selection) {
                    completedTasks.add(t);
                    System.out.println("Ok, '" + t.getTitle() + "' is marked complete.");
                    System.out.println();
                }
            }
            //removing completed task from tasks ArrayList
            tasks.remove(selection - 1);
        }
    }

    //check whether there are any tasks in the tasks ArrayList, and if not, takes user back to mainMenu
    public boolean checkForTasks(){
        if (tasks.isEmpty()) {
            System.out.println("You have no tasks to complete!");
            System.out.println();
            return true;
        } else {
            return false;
        }
    }
}

