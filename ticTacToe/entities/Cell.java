package tictactoe.entities;

import tictactoe.enums.Symbol;

public class Cell {
     private Symbol symbol;
    public Cell(Symbol symbol){
        this.symbol=symbol;
    }
   
    
    public Symbol getSymbol() {
        return symbol;
    }
    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

}
