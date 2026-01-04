package taskManagementSystem.entities;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import taskManagementSystem.enums.TaskPriority;
import taskManagementSystem.enums.TaskStatus;
import taskManagementSystem.exception.CommentException;
import taskManagementSystem.exception.TaskException;
import taskManagementSystem.exception.UserException;

public class Task {
    private final String taskId;
    
    private  String taskName;
    private String taskDescription;
    private TaskPriority taskPriority;
    private TaskStatus taskStatus;
    private List<Comment> comments;
    private User assignee;
    public Task(String taskName,String taskDescription,TaskPriority taskPriority,User assignee){
        if(taskName==null||taskDescription==null||taskName.isBlank()||taskDescription.isBlank()){
            throw new TaskException("Task name or description is invalid");
        }
        this.taskDescription=taskDescription;
        this.taskName=taskName;
        this.assignee=assignee;
        this.taskId=UUID.randomUUID().toString();
        this.taskStatus=TaskStatus.YET_TO_START;
        this.taskPriority=taskPriority!=null?taskPriority:TaskPriority.LOW;
        this.comments=new ArrayList<>();
        
    }
    public String getTaskId() {
        return taskId;
    }
    public String getTaskName() {
        return taskName;
    }
    public void setTaskName(String taskName) {
                if(taskName==null||taskName.isBlank()){
            throw new TaskException("Task name is invalid");
        }
        this.taskName = taskName;
    }
    public String getTaskDescription() {
        return taskDescription;
    }
    public void setTaskDescription(String taskDescription) {
                if(taskDescription==null||taskDescription.isBlank()){
            throw new TaskException("Task description is invalid");
        }
        this.taskDescription = taskDescription;
    }
    public TaskPriority getTaskPriority() {
        return taskPriority;
    }
    public void setTaskPriority(TaskPriority taskPriority) {
         throwExceptionWhenTaskDone();
        this.taskPriority = taskPriority;
        
    }
    public TaskStatus getTaskStatus() {
        return taskStatus;
    }
    public void setTaskStatus(TaskStatus taskStatus) {
        if(taskStatus==null){
            throw new TaskException("Task status can't be null");
        }
        throwExceptionWhenTaskDone();
        if(taskStatus.getStatusValue()<this.taskStatus.getStatusValue()){
            throw new TaskException("Task can't be go in backward state");
        }
        this.taskStatus = taskStatus;
    }
    public List<Comment> getComments() {
        return comments;
    }
    public void addComments(Comment comment) {
         if(comment==null){
            throw new CommentException("Comment is null");
        }
        this.comments.add(comment);
    }
    public User getAssignee() {
        return assignee;
    }
    public void setAssignee(User assignee) {
         if(assignee==null){
            throw new UserException("Assignee can't be null");
        }
        throwExceptionWhenTaskDone();
        
       
        if(!this.taskStatus.equals(TaskStatus.YET_TO_START)){
            throw new TaskException("Assignee can't be changed when someone else has started the task");
        }
        
        if(this.assignee==null){
             this.assignee = assignee;            
        }
        else{
            this.assignee.removeTask(this);
            this.assignee=assignee;
            this.assignee.addTask(this);
        }

    }
    private void throwExceptionWhenTaskDone(){
         if(this.taskStatus.equals(TaskStatus.DONE)){
            throw new TaskException("Assignee can't be changed after task is completed");
        }
    }

   
   
    public static Comparator<Task> comparator(){
        Comparator<Task>comparator=new Comparator<Task>() {
            @Override
            public int compare(Task a,Task b){
                TaskPriority taskA=a.getTaskPriority();
                TaskPriority taskB=b.getTaskPriority();
                if(taskA.getTaskPriorityValue()<taskB.getTaskPriorityValue())return -1;
                else if(taskA.getTaskPriorityValue()==taskB.getTaskPriorityValue())return 0;
                else
                return 1;
                //  return taskA.getTaskPriorityValue().compareTo(b.getTaskPriority().getTaskPriorityValue());
            }
        };
        return comparator;
    }
    public int getTaskPriorityValue(){
        return this.taskPriority.getTaskPriorityValue();
    }
    
}
