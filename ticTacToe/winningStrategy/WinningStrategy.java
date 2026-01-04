package tictactoe.winningStrategy;

import tictactoe.entities.Board;
import tictactoe.entities.Player;

public interface WinningStrategy {
    boolean checkWinner(Player player,Board board);
}
