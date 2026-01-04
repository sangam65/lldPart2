package ticTacToe.winningStrategy;

import ticTacToe.entities.Board;
import ticTacToe.entities.Player;
import ticTacToe.enums.Symbol;

public class ColumnWinningStrategy implements WinningStrategy{

    @Override
    public boolean checkWinner(Player player, Board board) {
        Symbol symbol=player.getSymbol();
        int sz=board.getSize();
        for(int col=0;col<sz;col++){
            boolean win=true;
            for(int row=0;row<sz;row++){
                if(!board.getCell(row, col).equals(symbol)){
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
