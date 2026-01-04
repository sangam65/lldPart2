package tictactoe.winningStrategy;

import tictactoe.entities.Board;
import tictactoe.entities.Player;
import tictactoe.enums.Symbol;

public class DiagonalWinningStrategy  implements WinningStrategy{
     @Override
    public boolean checkWinner(Player player, Board board) {
        Symbol symbol=player.getSymbol();
        int sz=board.getSize();
        int row=0,col=0;
        boolean win=true;
        while(row<sz&&col<sz){
            if(!board.getCell(row, col).equals(symbol)){
                    win=false;
                    break;
                }
            row++;
            col++;
        }
        if(win==true)return true;
        row=0;col=sz-1;
        while(row<sz&&col>=0){
            if(!board.getCell(row, col).equals(symbol)){
                    win=false;
                    break;
                }
            row++;
            col--;
        }

        return win;
    }
}
