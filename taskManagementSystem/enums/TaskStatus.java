package taskManagementSystem.enums;

public enum TaskStatus {
    YET_TO_START("YET_TO_START",0),
    IN_PROGRESS(" IN_PROGRESS",1),
    ON_HOLD("ON_HOLD",1),
    DONE("DONE",2);
    private final String statusCode;
    private final int statusValue;
    public String getStatusCode() {
        return statusCode;
    }
    public int getStatusValue() {
        return statusValue;
    }
    private TaskStatus(String statusCode,int statusValue){
        this.statusCode=statusCode;
        this.statusValue=statusValue;
    }
    

}
