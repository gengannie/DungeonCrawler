package model;

public interface ObjectsInWorld {
    boolean getPresence();

    boolean getCanTouch();

    int getPosX();

    int getPosY();

    void performBehavior(Hero h);
}
