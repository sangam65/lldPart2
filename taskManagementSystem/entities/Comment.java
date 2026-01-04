package taskManagementSystem.entities;

import taskManagementSystem.exception.CommentException;

public class Comment {
    private String comment;
    private final User user;
    private final Task task;
    
    public Comment(String comment, User user, Task task) {
        if(comment==null||comment.isBlank()||user==null||task==null){
            throw new CommentException("Either comment or user or task is null which makes this an invalid comment");
        }
        this.comment = comment;
        this.user = user;
        this.task = task;
    }
    public String getComment() {
        return comment;
    }
    public void updateComment(String comment) {
        if(comment==null||comment.isBlank()){
            throw new CommentException("Invalid comment: either comment is empty or null");
        }
        this.comment = comment;
    }
    public User getUser() {
        return user;
    }
    public Task getTask() {
        return task;
    }
}
