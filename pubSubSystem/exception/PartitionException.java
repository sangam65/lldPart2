package pubSubSystem.exception;

public class PartitionException extends RuntimeException{
    public PartitionException(String message){
        super(message);
    }
}
