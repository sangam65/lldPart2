package pubSubSystem.message;


import java.time.LocalDate;

public class Message {
    private final LocalDate timeStamp;
    public LocalDate getTimeStamp() {
        return timeStamp;
    }
    private final String message;
   
    public String getMessage() {
        return message;
    }
    public Message( String message) {
        this.timeStamp=LocalDate.now();
        this.message = message;
    }
    public void printMessage(){
        System.out.println(message+" "+timeStamp);
    }
}
