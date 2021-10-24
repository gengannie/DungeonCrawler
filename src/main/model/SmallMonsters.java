package model;

// Superclass of all the specific monsters, contain default behavior
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

    //EFFECTS: constructs a small monster with default characteristics, world location, and attack points
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

    //MODIFIES: this
    //EFFECTS: changes if this monster is in sight of the  hero or not

    public void changeThisSight() {
        inSightOfHero = !inSightOfHero;
    }

    //MODIFIES: Hero
    //EFFECTS: "attacks" hero by the number of attackPoints it has
    @Override
    public void attack(Hero h) {
        h.getAttacked(attackPoints);
    }

    @Override
    //MODIFIES: this
    //EFFECTS: changes the position of the monster by x and y amounts
    public void updatePosition(int x, int y) {
        if (canMove) {
            if (x > moveAmount) {
                x = moveAmount;
            }
            if (y > moveAmount) {
                y = moveAmount;
            }
            posX += x;
            posY += y;
        }
    }

    //MODIFIES: this
    //EFFECTS: health decreases by amount and changes state of isDead if health <= 0
    @Override
    public void getHit(int amount) {
        health -= amount;
        if (health <= 0) {
            isDead = true;
        }
    }

    public int getHealth() {
        return health;
    }

    @Override
    public boolean getCanMove() {
        return canMove;
    }

    @Override
    // EFFECTS: changes canMove to true if itself is false, false otherwise
    public void changeCanMove() {

        canMove = !canMove;
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

    public boolean getIsDead() {
        return isDead;
    }

    //MODIFIES: this
    //EFFECTS: sets this monster's isDead variable for saving purposes
    public void setIsDead(boolean dead) {
        this.isDead = dead;
    }

    //REQUIRES: health to be of a reasonable health
    //MODIFIES: this
    //EFFECTS: sets this monster's health to a certain number for saving purposes
    public void setHealth(int health) {
        this.health = health;
        if (health <= 0) {
            isDead = true;
        }
    }


}
