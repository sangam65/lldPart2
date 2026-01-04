package taskmanagementsystem.enums;

public enum TaskPriority {
    LOW("LOW",0),
    MEDIUM("MEDIUM",1),
    HIGH("HIGH",2);
    private final String taskPriorityName;
    private final int taskPriorityValue;
    public String getTaskPriorityName() {
        return taskPriorityName;
    }
    public int getTaskPriorityValue() {
        return taskPriorityValue;
    }
    private TaskPriority(String taskPriorityName,int taskPriorityValue){
        this.taskPriorityName=taskPriorityName;
        this.taskPriorityValue=taskPriorityValue;
    }
    
}
