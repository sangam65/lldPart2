package ticTacToe.exception;
public class CellOutOfBoundException extends RuntimeException{
    public CellOutOfBoundException(String message){
        super(message);
    }

}
