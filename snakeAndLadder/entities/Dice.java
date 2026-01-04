package snakeAndLadder.entities;

import snakeAndLadder.exception.DiceException;

public class Dice {
    private final int faces;

    public Dice(int faces) {
        if(faces<=3){
            throw new DiceException("Faces shall be minimum 4");
        }
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
