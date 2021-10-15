package model;

// Abstract class outlining some variables of a generic object (cannot move)
public abstract class GenericObject implements ObjectsInWorld {
    protected boolean isInWorld;
    protected boolean canTouch;
    protected int posX;
    protected int posY;

    // EFFECTS: initializes generic object with default attributes with certain x,y position
    public GenericObject(boolean inWorld, boolean touch, int x, int y) {
        isInWorld = inWorld;
        canTouch = touch;
        posX = x;
        posY = y;
    }

    @Override
    public boolean getPresence() {
        return isInWorld;
    }

    //MODIFIES: this
    //EFFECTS: if isInWorld is true, make if false and vice versa
    public void changePresence() {
        isInWorld = !isInWorld;
    }

    @Override
    public boolean getCanTouch() {
        return canTouch;
    }

    @Override
    public int getPosX() {
        return posX;
    }

    @Override
    public int getPosY() {
        return posY;
    }

}
