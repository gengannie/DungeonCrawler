package model;

public interface ObjectsInWorld {
    boolean getPresence();

    boolean canBeTouched();

    int getPosX();

    int getPosY();

    void performBehavior(Hero h);
}
