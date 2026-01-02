public class Board {
    private final int sz;
    private final Cell cell[][];
    private int moves;

    public Board(int sz){
        this.sz=sz;
        this.cell=new Cell[sz][sz];
        this.moves=0;
        initializeBoard(sz);
    }
    private void initializeBoard(int sz){
        for(int i=0;i<sz;i++){
            for(int j=0;j<sz;j++){
                cell[i][j]=new Cell(Symbol.EMPTY);
            }
        }
    }
    public int getSize(){
        return sz;
    }
    public boolean validCell(int x,int y) throws CellOutOfBoundException{
        if(x>=0&&x<sz&&y>=0&&y<sz){
            // if(cell[x][y].getSymbol()!=Symbol.EMPTY){
            //     System.out.println("Cell is already occupied");
            //         return false;
            // }
            return true;
        }   
        else{
           throw new CellOutOfBoundException("Coordinates are invalid");
        }
    }
    public boolean cellOccupied(int x,int y) throws CellAlreadyOccupiedException{
        if(!cell[x][y].getSymbol().equals(Symbol.EMPTY)){
                System.out.println("Cell is already occupied");
                    throw new CellAlreadyOccupiedException("Cell is already occupied");
            }
            return true;
    }
    public Symbol getCell(int x,int y) throws CellOutOfBoundException{
        validCell(x, y);
        return cell[x][y].getSymbol();
        
      
    }
    public void placeSymbolOnCell(int x,int y,Symbol symbol) throws CellAlreadyOccupiedException,CellOutOfBoundException{
        validCell(x, y);
        cellOccupied(x, y);
        cell[x][y].setSymbol(symbol);
        this.moves++;
    }
    public boolean isBoardFull(){
        return sz==moves;
    }

    public void printBoard(){
        for(int i=0;i<sz;i++){
            for(int j=0;j<sz;j++){
                System.out.print(cell[i][j].getSymbol()+" ");
            }
            System.out.println();
        }
    }

}
