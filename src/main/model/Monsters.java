package model;

public interface Monsters {
    //EFFECTS: return integer of how many damage this monster deals
    void attack(Hero h);

    //EFFECTS: return integer of how much this monster can move
    void updatePosition(int x, int y);

    //EFFECTS: returns true if this monster is dead
    boolean getHit(int amount);

    boolean getCanMove();

    void changeCanMove();

}
