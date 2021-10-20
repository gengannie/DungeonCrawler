package model;

// interface for all types monsters, currently only SmallMonsters implement this
public interface Monsters {
    //MODIFIES: Hero
    //EFFECTS: deal damage to hero
    void attack(Hero h);

    //MODIFIES: this
    //EFFECTS: return integer of how much this monster can move
    void updatePosition(int x, int y);

    //EFFECTS: returns true if this monster is dead
    void getHit(int amount);

    boolean getCanMove();

    //EFFECTS: changes whether or not this monster can or cannot move
    void changeCanMove();

}
