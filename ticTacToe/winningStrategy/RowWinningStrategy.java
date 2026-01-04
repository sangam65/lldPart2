package ticTacToe.winningStrategy;

import ticTacToe.entities.Board;
import ticTacToe.entities.Player;
import ticTacToe.enums.Symbol;

public class RowWinningStrategy implements WinningStrategy {

    @Override
    public boolean checkWinner(Player player, Board board) {
        Symbol symbol=player.getSymbol();
        int sz=board.getSize();
        for(int i=0;i<sz;i++){
            boolean win=true;
            for(int col=0;col<sz;col++){
                if(!board.getCell(i, col).equals(symbol)){
                    win=false;
                    break;
                }
            }
            if(win==true){
                return true;
            }
        }
        return false;
    }

}
