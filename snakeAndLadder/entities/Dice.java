package snakeandladder.entities;

import snakeandladder.exception.DiceException;

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
  

        return (int)(Math.random() * faces) + 1;
    }

    

}
