package model;

public interface ObjectsInWorld {
    boolean presence();

    boolean canBeTouched();

    int getPosX();

    int getPosY();

    void getBehavior(Hero h);
}
