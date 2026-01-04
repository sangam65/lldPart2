package snakeandladder.entities;

import snakeandladder.exception.InvalidPlayerException;

public class Player {
    private String name;
    private int position;
    public Player(String name) {
        if(name==null||name.isBlank()){
            throw new InvalidPlayerException("Player name can't be null or empty");
        }
        this.name = name;
        this.position = 1;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPosition() {
        return position;
    }
    public void setPosition(int position) {
        this.position = position;
    }
    
}
