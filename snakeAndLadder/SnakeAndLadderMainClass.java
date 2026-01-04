package snakeandladder;

import java.util.List;

import snakeandladder.entities.Board;
import snakeandladder.entities.Dice;
import snakeandladder.entities.Player;
import snakeandladder.entities.SnakeAndLadder;
import snakeandladder.exception.GameException;

public class SnakeAndLadderMainClass {
    public static void main(String []args){
        Board board=new Board(10);
        board.addLadder(1, 3);
        board.addSnake(9, 2);

        Dice dice=new Dice(4);
        Player player1=new Player("Sangam");
        Player player2=new Player("Anjali");
        List<Player>players=List.of(player1,player2);
        SnakeAndLadder snakeAndLadder=new SnakeAndLadder(board, dice, players);

    Player player;
        while(true){
            try{
                snakeAndLadder.rollDice();
            }
            catch(GameException e){
                player=snakeAndLadder.getWinningPlayer();
                break;
            }
           
        }
       
        if(player!=null ){
            System.out.println(player.getName());
        }
       
    }
}
