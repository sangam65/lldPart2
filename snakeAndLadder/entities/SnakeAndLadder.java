package snakeAndLadder.entities;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import snakeAndLadder.GameStatus;
import snakeAndLadder.exception.InvalidPlayerException;

public class SnakeAndLadder {
    private final Board board;
    private final Dice dice;
    public Board getBoard() {
        return board;
    }
    public Dice getDice() {
        return dice;
    }
    private Deque<Player>players;
    private Player winningPlayer;
    private GameStatus gameStatus;

    public SnakeAndLadder(Board board,Dice dice,List<Player> playerList){
        checkPlayerValid(playerList);
        this.players=new ArrayDeque<>();
        for(Player player:playerList){
            players.addLast(player);
        }
        this.board=board;
        this.dice=dice;
        this.gameStatus=GameStatus.IN_PROGRESS;
    
        
    }
    private void checkPlayerValid(List<Player>players){
        if(players==null){
            throw new InvalidPlayerException("Players list is null");
        }
        for(Player player:players){
            if(player==null){
                throw new InvalidPlayerException("Player can't be null");
            }
        }
    }
    public void rollDice() throws RuntimeException{
        checkGameFinished();
        Player player=players.pollFirst();
        int numberofDice=dice.rollDice();
        board.updatePlayerPositionIfPossible(player, numberofDice);
        
        if(player.getPosition()==board.getBoardSize()){
            this.winningPlayer=player;
            this.gameStatus=GameStatus.FINISHED;
            System.out.println("Game is won by "+player.getName());
            return;
        }
        players.addLast(player);
    

    }
    private void checkGameFinished(){
       if(gameStatus.equals(GameStatus.FINISHED)){
            System.out.println("Game is already fnished");
            throw new RuntimeException("Game is already fnished");
        }
    }
    public Player getWinningPlayer(){
        if(this.winningPlayer==null){
            throw new InvalidPlayerException("Game is ongooing");
        }
        return this.winningPlayer;
    }
}
