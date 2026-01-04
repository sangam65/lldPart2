package lru.exception;

public class CapacityConstrainException extends RuntimeException{
 public CapacityConstrainException(String message){
        super(message);
    }
}
