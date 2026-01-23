package taskmanagementsystem.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

import taskmanagementsystem.enums.TaskPriority;
import taskmanagementsystem.enums.TaskStatus;
import taskmanagementsystem.exception.CommentException;
import taskmanagementsystem.exception.TaskException;
import taskmanagementsystem.exception.UserException;

public class User {
    private final String userId;
    private final String name;
    private List<Comment> comments;
    private TreeSet<Task> tasksToDo;
    private List<Task> completedTask;
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

    public User(String name) {
        if (name == null || name.isBlank()) {
            throw new UserException("name can't be empty or null");
        }
        this.userId = UUID.randomUUID().toString();
        this.name = name;
        this.comments = new ArrayList<>();
        this.tasksToDo = new TreeSet<>((Task a,Task b)->{
                TaskPriority taskA=a.getTaskPriority();
                TaskPriority taskB=b.getTaskPriority();
                if(taskA.getTaskPriorityValue()<taskB.getTaskPriorityValue())return -1;
                else if(taskA.getTaskPriorityValue()==taskB.getTaskPriorityValue())return 0;
                else
                return 1;
                //  return taskA.getTaskPriorityValue().compareTo(b.getTaskPriority().getTaskPriorityValue());
            });
        this.completedTask = new ArrayList<>();
        this.currentTask = null;
    }

    public void addTask(Task task) {
        if (task == null || task.getTaskStatus().equals(TaskStatus.DONE)) {
            throw new TaskException("Task can't be added as task is null");

        }
        if (tasksToDo.contains(task)) {
            throw new TaskException("Task is already added to user");
        }
        tasksToDo.add(task);
        if (currentTask == null) {
            currentTask = task;
            changeTaskStatus();
        } else {
            if (currentTask.getTaskPriorityValue() < task.getTaskPriorityValue()) {
                currentTask.setTaskStatus(TaskStatus.ON_HOLD);
                currentTask = task;
                changeTaskStatus();
            }
        }

    }

    public void removeTask(Task task) {
        if (!tasksToDo.contains(task)) {
            throw new TaskException("Task is not given to the user");
        }
        tasksToDo.remove(task);
        changeTask(task);
    }

    public void addComment(Comment comment) {
        if (comment == null) {
            throw new CommentException("Comment is null");
        }
        comments.add(comment);
    }

    public void completeTask(Task task) {
        if (!tasksToDo.contains(task)) {
            throw new TaskException("Task is not given to the user");
        }
        if (currentTask == task) {
            completedTask.add(task);
            tasksToDo.remove(task);
            changeTask(task);
        } else {
            throw new TaskException("Task is not given to the user so this user's can't complete the task");
        }

    }

    private void changeTask(Task task) {
        if (task == currentTask) {
            if (!tasksToDo.isEmpty()) {
                Task nxtTask = tasksToDo.first();
                currentTask = nxtTask;
                changeTaskStatus();
            } else {
                currentTask = null;
            }
        }
    }

    private void changeTaskStatus() {
        if (this.currentTask != null) {
            this.currentTask.setTaskStatus(TaskStatus.IN_PROGRESS);
        }
    }

    public void taskPriorityChanged(Task task) {
        if (!tasksToDo.contains(task)) {
            throw new TaskException("Task is not given to the user");
        }
        if (task == null) {
            throw new TaskException("Task can't be added as task is null");

        }
        if (task != currentTask) {
            if (currentTask.getTaskPriorityValue() < task.getTaskPriorityValue()) {
                currentTask.setTaskStatus(TaskStatus.ON_HOLD);
                currentTask = task;
                changeTaskStatus();
            }
        }
    }
}
