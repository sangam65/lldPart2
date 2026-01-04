package snakeAndLadder;

import java.util.List;

import snakeAndLadder.entities.Board;
import snakeAndLadder.entities.Dice;
import snakeAndLadder.entities.Player;
import snakeAndLadder.entities.SnakeAndLadder;

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

        int count=0;
        while(count<10){
            count++;
            snakeAndLadder.rollDice();
           
        }
        Player player=snakeAndLadder.getWinningPlayer();
        if(player!=null ){
            System.out.println(player.getName());
        }
       
    }
}
