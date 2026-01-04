package taskManagementSystem.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import taskManagementSystem.enums.TaskPriority;
import taskManagementSystem.enums.TaskStatus;
import taskManagementSystem.exception.TaskException;
import taskManagementSystem.exception.UserException;

public class TaskManagement {
    private final Map<String, User> users;
    private final Map<String, Task> tasks;
    private final Map<String, Task> completedTasks;

    public TaskManagement() {
        this.users = new HashMap<>();
        this.tasks = new HashMap<>();
        this.completedTasks = new HashMap<>();
    }

    public synchronized List<User> getUsers() {
        List<User>userList=new ArrayList<>();
        for(User user:users.values()){
            userList.add(user);
        }
        return userList;
    }

    public synchronized List<Task> getOngoingTasks() {
      List<Task>ongoingTasks=new ArrayList<>();
        for(Task task:tasks.values()){
            ongoingTasks.add(task);
        }
        return ongoingTasks;
    }

  

    public synchronized void addUser(User user) {
        if (user == null) {
            throw new UserException("user can be null");
        }
        if (users.containsKey(user.getUserId())) {
            throw new UserException("user is already added");
        }
        users.put(user.getUserId(), user);
    }

    public synchronized void addTask(Task task) {
        if (task == null) {
            throw new TaskException("task can be null");
        }
        if (tasks.containsKey(task.getTaskId())) {
            throw new TaskException("task is already added");
        }
           User assignee=task.getAssignee();
        if(assignee!=null&&!users.containsKey(assignee.getUserId())){
                throw new TaskException("Assignee does not exist in the system");
        }
        if (completedTasks.containsKey(task.getTaskId())) {
            throw new TaskException("task is already completed");
        }
        
        tasks.put(task.getTaskId(), task);
      
        if(assignee!=null){
            assignee.addTask(task);
        }
    }

 

    public synchronized void updateTaskPriority(Task task, TaskPriority taskPriority) {
        if (!tasks.containsKey(task.getTaskId())) {
            throw new TaskException("task is not added");
        }
        task.setTaskPriority(taskPriority);
        User assignee=task.getAssignee();
        if(assignee!=null){
            assignee.taskPriorityChanged(task);
        }
      
    }

    public synchronized void completeTask(Task task){
        if (!tasks.containsKey(task.getTaskId())) {
            throw new TaskException("task is not added");
        }
        if(completedTasks.containsKey(task.getTaskId())){
            throw new TaskException("Task is already completed");
        }
        User user=task.getAssignee();
        if(user==null){
            throw new TaskException("Task can't be completed yet as task is not given to user");
        }
        if(user.getCurrentTask()==null||!user.getCurrentTask().equals(task)){
            throw new TaskException("Task can't be completed yet as user is currently doing some other task");
        }
        task.setTaskStatus(TaskStatus.DONE);
    
        user.completeTask(task);
        completedTasks.put(task.getTaskId(),task);
        tasks.remove(task.getTaskId());

    }
    public synchronized List<Task> getCompletedTasks(){
        List<Task>finishedTasks=new ArrayList<>();
        for(Task task:completedTasks.values()){
            finishedTasks.add(task);
        }
        return finishedTasks;

    }

}
