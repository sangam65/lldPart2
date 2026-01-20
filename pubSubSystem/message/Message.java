package pubSubSystem.message;



import java.time.LocalDateTime;

public class Message {
    private final LocalDateTime timeStamp;
    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }
    private final String message;
   
    public String getMessage() {
        return message;
    }
    public Message( String message) {
        this.timeStamp=LocalDateTime.now();
        this.message = message;
    }
    public void printMessage(){
        System.out.println(message+" "+timeStamp);
    }
}
