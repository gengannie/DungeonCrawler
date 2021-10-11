package model;

// Abstract class outlining some variables of a generic object (cannot move)
public abstract class GenericObject implements ObjectsInWorld {
    protected boolean isInWorld;
    protected boolean canTouch;
    protected int posX;
    protected int posY;

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

    public void changePresence() {
        isInWorld = !isInWorld;
    }

    @Override
    public boolean canBeTouched() {
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
