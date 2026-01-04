package taskManagementSystem.entities;

import java.util.HashMap;
import java.util.Map;

import taskManagementSystem.exception.TaskException;
import taskManagementSystem.exception.UserException;

public class TaskManagement {
    private final Map<String,User> users;
    private final Map<String,Task>tasks;
    private final Map<String,Task>completedTasks;
    
    public TaskManagement() {
        this.users = new HashMap<>();
        this.tasks =new HashMap<>();
        this.completedTasks = new HashMap<>();
    }
    public Map<String, User> getUsers() {
        return users;
    }
    public Map<String, Task> getTasks() {
        return tasks;
    }
    public Map<String, Task> getCompletedTasks() {
        return completedTasks;
    }
    public synchronized void addUser(User user){
        if(user==null){
            throw new UserException("user can be null");
        }
        if(users.containsKey(user.getUserId())){
              throw new UserException("user is already added");
        }
        users.put(user.getUserId(), user);
    }
    public synchronized void addTask(Task task){
        if(task==null){
            throw new TaskException("task can be null");
        }
        if(tasks.containsKey(task.getTaskId())){
             throw new TaskException("task is already added");
        }
        if(completedTasks.containsKey(task.getTaskId())){
              throw new TaskException("task is already completed");
        }
        tasks.put(task.getTaskId(), task);
    }
    
    
}
