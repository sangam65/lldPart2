package snakeAndLadder.entities;

public class Dice {
    private final int faces;

    public Dice(int faces) {
        this.faces = faces;
    }

    public int getFaces() {
        return faces;
    }
    public int rollDice(){
        int random=(int)(Math.random()*faces);
        int rolling=random%faces;
        rolling=rolling==0?faces:rolling;
        return rolling;
    }

    

}
