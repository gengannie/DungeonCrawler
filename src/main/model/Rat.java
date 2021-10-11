package model;

// A subclass of SmallMonsters that represent the behaviors and characteristics of a small rat
public class Rat extends SmallMonsters {

    public Rat(int posX, int posY) {
        super(1, 4,2, posX, posY);
        nameOfMonster = "Rat";
    }
}
