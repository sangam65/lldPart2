package snakeAndLadder.entities;

import java.util.HashMap;

import snakeAndLadder.exception.LadderAndSnakeInputException;

public class Board {
    private final HashMap<Integer,Integer> ladder;
    private final HashMap<Integer,Integer>snakes;
    private final int boardSize;
    public Board( int boardSize) {
        this.ladder = new HashMap<>();
        this.snakes =new HashMap<>();
        this.boardSize = boardSize;
    }
    public HashMap<Integer, Integer> getLadder() {
        return ladder;
    }
    public HashMap<Integer, Integer> getSnakes() {
        return snakes;
    }
    public int getBoardSize() {
        return boardSize;
    }
    public synchronized void addLadder(int startPoint,int endPoint){
        if(startPoint>=endPoint||startPoint<=0||endPoint>=this.boardSize){
            throw new LadderAndSnakeInputException("startPoint or endPoint is invalid");
        }
        ladder.put(startPoint, endPoint);
    }
    public synchronized void addSnake(int startPoint,int endPoint){
        if(endPoint>=startPoint||startPoint>=this.boardSize||endPoint<=0){
            throw new LadderAndSnakeInputException("startPoint or endPoint is invalid");
        }
        snakes.put(startPoint, endPoint);
    }
    public void updatePlayerPositionIfPossible(Player player,int position){
        int playerNextPosition=player.getPosition()+position;
        if(playerNextPosition>boardSize){
            System.out.println("Position is out of board so ,the player's position does not change,try on the next chance");
            return;
        }
        player.setPosition(playerNextPosition);

    }

}
