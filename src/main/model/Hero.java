package model;

public class Hero {
    private static final int NUM_OF_TURNS = 2;
    private static final int VISIBILITY = 5;
    private static final int MAX_NUM_OF_CARDS = 10;
    public static final int MAX_HEALTH = 10;

    private String myName;
    private int moveSquares;
    private CardsList cardInventory;
    private int health;
    private int manaBar;
    private int posX;
    private int posY;
    private int coinsInInventory;
    private int hitPoints;

    //EFFECTS: initializes hero with certain move squares, name, max health, and no mana
    public Hero(int canMove, String name) {
        myName = name;
        moveSquares = canMove;
        health = MAX_HEALTH;
        manaBar = 0;
        cardInventory = new CardsList();
        posX = 0;
        posY = 0;
        coinsInInventory = 0;
        hitPoints = 3;

    }

    //MODIFIES: this
    //EFFECTS: increase number of health points by healPoints if health is less than MAX_HEALTH, else returns health

    public int heal(int healPoints) {
        if (health < MAX_HEALTH) {
            if (health + healPoints > MAX_HEALTH) {
                return MAX_HEALTH;
            } else {
                return health + healPoints;
            }
        } else {
            return health;
        }

    }

    //MODIFIES: this
    //EFFECTS: decrease health by attackPoints. if health <= 0, return false
    public boolean getAttacked(int attackPoints) {
        health -= attackPoints;
        return (health > 0);
    }

    //EFFECTS: returns max number of turns
    public int getMaxTurns() {
        return NUM_OF_TURNS;
    }

    //EFFECTS: return current health value
    public int getCurrentHealth() {
        return health;
    }

    //MODIFIES: this
    //EFFECTS: changes hero's x and y position
    public void moveHero(int x, int y) {
        posX += x;
        posY += y;
    }

    //EFFECTS: returns this hero's x position in world
    public int getPosX() {
        return posX;
    }

    //EFFECTS: returns this hero's y position in world
    public int getPosY() {
        return posY;
    }

    //EFFECTS: returns this hero's coins
    public int getCoinsInInventory() {
        return coinsInInventory;
    }

    //EFFECTS: returns this hero's hitPoints
    public int getHitPoints() {
        return hitPoints;
    }

    //MODIFIES: this
    //EFFECTS: modifies this hero's movePoints by amount
    public void changeMovePoints(int amount) {
        hitPoints += amount;
    }

    //EFFECTS: return list of cards in inventory
    public CardsList seeListOfCards() {
        return cardInventory;

    }

    //MODIFIES: this
    //EFFECTS: adds coinAmount to coins this hero has
    public void addCoins(int coinAmount) {
        coinsInInventory += coinAmount;
    }
}
