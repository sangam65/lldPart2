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
        if(snakes.containsKey(startPoint)){
            throw new LadderAndSnakeInputException("this position is already taken in snakes ");
        }
        ladder.put(startPoint, endPoint);
    }
    public synchronized void addSnake(int startPoint,int endPoint){
        if(endPoint>=startPoint||startPoint>=this.boardSize||endPoint<=0){
            throw new LadderAndSnakeInputException("startPoint or endPoint is invalid");
        }
        if(ladder.containsKey(startPoint)){
            throw new LadderAndSnakeInputException("this position is already taken in ladders ");
        }
        snakes.put(startPoint, endPoint);
    }
    public void updatePlayerPositionIfPossible(Player player,int position){
        int playerNextPosition=player.getPosition()+position;
        if(playerNextPosition>boardSize){
            System.out.println("Position is out of board so ,the player's position does not change,try on the next chance");
            return;
        }

        if(ladder.containsKey(playerNextPosition)){
            playerNextPosition=ladder.get(playerNextPosition);
        }
        else if(snakes.containsKey(playerNextPosition)){
            playerNextPosition=snakes.get(playerNextPosition);
        }
        player.setPosition(playerNextPosition);

    }

}
