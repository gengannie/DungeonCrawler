package model;

// interface for generic objects in world that cannot move by itself
public interface ObjectsInWorld {
    boolean getPresence();

    boolean getCanTouch();

    int getPosX();

    int getPosY();

    //MODIFIES: Hero
    //EFFECTS: performs behavior on hero, specific to this object's characteristic
    void performBehavior(Hero h);
}
