package lru.exception;

public class KeyNotFoundException extends RuntimeException {
    public KeyNotFoundException(){
        super("Key does not exist");
    }
}
