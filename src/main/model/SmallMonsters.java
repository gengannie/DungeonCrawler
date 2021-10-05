package model;

//TODO: add class generic descriptions
// TODO: add awareness of monsters to the hero
// TODO: start to add tests on classes: Hero, SmallMonsters, Cards
public class SmallMonsters implements Monsters {
    protected int attackPoints;
    protected int moveAmount;
    protected boolean canMove;
    protected boolean isDead;
    protected int health;
    protected int posX;
    protected int posY;
    protected String nameOfMonster;
    protected boolean inSightOfHero;

    public SmallMonsters(int attack, int move, int health, int posX, int posY) {
        attackPoints = attack;
        moveAmount = move;
        isDead = false;
        this.health = health;
        canMove = true;
        this.posX = posX;
        this.posY = posY;
        inSightOfHero = false;
    }

    //EFFECTS: returns true if monster is in sight of the hero
    public boolean getIfInSight() {
        return (inSightOfHero);
    }

    public void changeThisSight() {
        inSightOfHero = !inSightOfHero;

    }

    @Override
    public void attack(Hero h) {
        h.getAttacked(attackPoints);
    }

    @Override
    //EFFECTS: changes the position of the monster by x and y amounts
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

    public String getName() {
        return nameOfMonster;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }


}
