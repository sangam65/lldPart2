package taskManagementSystem.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

import taskManagementSystem.enums.TaskStatus;
import taskManagementSystem.exception.CommentException;
import taskManagementSystem.exception.TaskException;
import taskManagementSystem.exception.UserException;

public class User {
    private final String userId;
    private final String name;
    private List<Comment>comments;
    private TreeSet<Task>tasksToDo;
    private List<Task>completedTask;
    private Task currentTask;
    public String getUserId() {
        return userId;
    }
    public String getName() {
        return name;
    }
    public List<Comment> getComments() {
        return comments;
    }
    public Set<Task> getTasksToDo() {
        return tasksToDo;
    }
    public List<Task> getCompletedTask() {
        return completedTask;
    }
    public Task getCurrentTask() {
        return currentTask;
    }
    public User( String name) {
            if(name==null||name.isBlank()){
                throw new UserException("name can't be empty or null");
            }
        this.userId = UUID.randomUUID().toString();
        this.name = name;
        this.comments = new ArrayList<>();
        this.tasksToDo =new TreeSet<>(Task.comparator());
        this.completedTask = new ArrayList<>();
        this.currentTask = null;
    }
    public void addTask(Task task){
        if(task==null||task.getTaskStatus().equals(TaskStatus.DONE)){
            throw new TaskException("Task can't be added as task is null");

        }
        if(tasksToDo.contains(task)){
            throw new TaskException("Task is already added to user");
        }
        tasksToDo.add(task);
    }
    public void removeTask(Task task){
        if(!tasksToDo.contains(task)){
            throw new TaskException("Task is not given to the user");
        }
        tasksToDo.remove(task);
        changeTask(task);
    }
    
    public void addComment(Comment comment){
        if(comment==null){
            throw new CommentException("Comment is null");
        }
        comments.add(comment);
    }
    public void completeTask(Task task){
        if(!tasksToDo.contains(task)){
                throw new TaskException("Task is not given to the user");
        }
        if(currentTask==task){
            completedTask.add(task);
             tasksToDo.remove(task);
             changeTask(task);
        }
        else{
             throw new TaskException("Task is not given to the user so this user's can't complete the task");
        }
       
    }
    private void changeTask(Task task){
        if(task==currentTask){
            if(!tasksToDo.isEmpty()){
                Task nxtTask=tasksToDo.first();
                currentTask=nxtTask;
            }
        }
    }
}
