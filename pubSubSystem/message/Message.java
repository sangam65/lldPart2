package pubSubSystem.message;

import java.time.Instant;

public class Message {
    private final Instant instant;
    private final String message;
    public Instant getInstant() {
        return instant;
    }
    public String getMessage() {
        return message;
    }
    public Message( String message) {
        this.instant = Instant.now();
        this.message = message;
    }
    public void printMessage(){
        System.out.println(message+" "+instant);
    }
}
