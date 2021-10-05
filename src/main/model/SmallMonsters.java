package model;

public class SmallMonsters implements Monsters {
    protected int attackPoints;
    protected int moveAmount;
    protected boolean canMove;
    protected boolean isDead;
    protected int health;
    protected int posX;
    protected int posY;

    public SmallMonsters(int attack, int move, int health, int posX, int posY) {
        attackPoints = attack;
        moveAmount = move;
        isDead = false;
        this.health = health;
        canMove = true;
        this.posX = posX;
        this.posY = posY;
    }

    @Override
    public int attack() {
        return attackPoints;
    }

    @Override
    public void updatePosition(int x, int y) {
        if (canMove) {
            posX += x;
            posY += y;
        }
    }

    //MODIFIES: this
    //EFFECTS: health decreases by amount and return false if health <= 0
    @Override
    public boolean getHit(int amount) {
        health -= amount;
        return (health > 0);
    }

    @Override
    public boolean getCanMove() {
        return canMove;
    }

    @Override
    public void changeCanMove() {
        canMove = false;
    }


}
