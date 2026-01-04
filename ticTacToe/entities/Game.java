
package ticTacToe.entities;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import ticTacToe.enums.GameStatus;
import ticTacToe.exception.CellAlreadyOccupiedException;
import ticTacToe.exception.CellOutOfBoundException;
import ticTacToe.winningStrategy.ColumnWinningStrategy;
import ticTacToe.winningStrategy.DiagonalWinningStrategy;
import ticTacToe.winningStrategy.RowWinningStrategy;
import ticTacToe.winningStrategy.WinningStrategy;

public class Game {
    private final Deque<Player>players;
    private Player currentPlayer;
    private Player winningPlayer;
    private GameStatus gameStatus;
    private final List<WinningStrategy>winningStrategies;
    private final Board board;
    public Deque<Player> getPlayers() {
        return players;
    }
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    public Player getWinningPlayer() {
        return winningPlayer;
    }
    public GameStatus getGameStatus() {
        return gameStatus;
    }
    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }
    public Board getBoard() {
        return board;
    }
    public Game(Player player1,Player player2,Board board){
        this.currentPlayer=player1;
        this.players=new ArrayDeque<>();
        this.players.add(player1);
        this.players.add(player2);
        this.gameStatus=GameStatus.IN_PROGRESS;
        this.winningPlayer=null;
        this.winningStrategies=List.of(new RowWinningStrategy(),new ColumnWinningStrategy(),new DiagonalWinningStrategy());
        this.board=board;
    }
    public void makePlayerMove(int x,int y) throws CellAlreadyOccupiedException,CellOutOfBoundException{
        try{
            if(matchFinished()){
                throw new RuntimeException("MATCH FINISHED");
            }
            this.currentPlayer=players.getFirst();
            
            this.board.placeSymbolOnCell(x, y, this.currentPlayer.getSymbol());
            //  check winnning if yes , change state
            boolean res=matchWon();
            if(res==true){
                System.out.println("Match won by "+this.currentPlayer.getName());
                changeGameStatus();
                return;
            }
            if(checkDraw()){
                drawStatus();
                return;
            }
            players.removeFirst();
            players.addLast(currentPlayer);

        }
        catch(CellOutOfBoundException | CellAlreadyOccupiedException e){
            throw e;
        }
    }
    public boolean matchFinished(){
        if(gameStatus.equals(GameStatus.IN_PROGRESS)){
            return false;
        }
        return true;
    }
    public boolean matchWon(){
        for(WinningStrategy winningStrategy:winningStrategies){
            if(winningStrategy.checkWinner(currentPlayer, board)){
             
                return true;
            }
        }
        
        return false;
    }
    private void changeGameStatus(){
         this.winningPlayer=currentPlayer;
        this.gameStatus=GameStatus.FINISHED;
    }
    private boolean checkDraw(){
        return this.board.isBoardFull();
    }
    private void drawStatus(){
       
        this.gameStatus=GameStatus.DRAW;
    }

}
