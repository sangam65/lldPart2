package ticTacToe.winningStrategy;

import ticTacToe.entities.Board;
import ticTacToe.entities.Player;

public interface WinningStrategy {
    boolean checkWinner(Player player,Board board);
}
