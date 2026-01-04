package taskManagementSystem;

import taskManagementSystem.entities.Task;
import taskManagementSystem.entities.TaskManagement;
import taskManagementSystem.entities.User;
import taskManagementSystem.enums.TaskPriority;

public class TaskManagementMainClass {
    public static void main(String[] args) {
        TaskManagement tm = new TaskManagement();
        User user1 = new User("Sangam");
        tm.addUser(user1);

        Task task1 = new Task("Low Priority", "Description", TaskPriority.LOW, user1);
        Task task2 = new Task("High Priority", "Description", TaskPriority.HIGH, user1);
        try {
            tm.addTask(task1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            tm.addTask(task2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            tm.addTask(task2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

       

        // Shows task switching due to priority
        System.out.println("Current Task: " + user1.getCurrentTask().getTaskName());

        // Complete and show next task
        tm.completeTask(user1.getCurrentTask());
        // tm.updateAssignee(task2, user1);
        System.out.println("Next Task: " + user1.getCurrentTask().getTaskName());
    }
}
