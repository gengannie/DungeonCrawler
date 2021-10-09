package model;

import java.util.ArrayList;

// Main character of this game
public class Hero {
    private static final int NUM_OF_TURNS = 2;
    public static final int VISIBLE = 3;
    private static final int MAX_NUM_OF_CARDS = 10;
    private static final int FULL_MANA = 10;
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
    private boolean isDead;

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
        isDead = false;

    }

    public int updateManaBar(int points) {
        manaBar += points;
        if (manaBar >= FULL_MANA) {
            manaBar -= FULL_MANA;
            cardInventory.getNewRandomCard();
        }
        return manaBar;

    }

    // EFFECTS: returns current experience (mana) bar value
    public int getManaBar() {
        return manaBar;
    }

    //MODIFIES: this
    //EFFECTS: increase number of health points by healPoints if health is less than MAX_HEALTH

    public void heal(int healPoints) {
        if (health == 0 && healPoints > 0) {
            isDead = false;
        }
        health += healPoints;
        if (health > MAX_HEALTH) {
            health = MAX_HEALTH;
        }

    }

    //MODIFIES: this
    //EFFECTS: decrease health by attackPoints. if health <= 0, set isDead to true
    public void getAttacked(int attackPoints) {
        health -= attackPoints;
        if (health <= 0) {
            isDead = true;
        }
    }

    //EFFECTS: returns true if hero is dead or not
    public boolean getIsDead() {
        return isDead;
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
    //EFFECTS: changes hero's x and y position, but x and y cannot be negative
    public void moveHero(int x, int y) {
        if (x > moveSquares) { //TODO: add test for this
            x = moveSquares;
        }
        if (y > moveSquares) {
            y = moveSquares;
        }
        posX += x;
        posY += y;
        if (posX < 0) {
            posX = 0;
        }
        if (posY < 0) {
            posY = 0;
        }
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
        moveSquares += amount;
    }

    public int getMoveSquares() {
        return moveSquares;
    }

    //MODIFIES: this
    //EFFECTS: adds coinAmount to coins this hero has
    public void addCoins(int coinAmount) {
        coinsInInventory += coinAmount;
    }

    //EFFECTS: returns card description of cards in inventory

    public ArrayList<String> getCardDes() {
        return cardInventory.getCardsDescription();
    }

    //REQUIRES: cardInventory to be not empty
    //MODIFIES: this
    //EFFECTS: modifies hero attributes according to behavior of card
    public void useCard(int indexInList, ArrayList<SmallMonsters> allMonsters) {
        Cards returnedCard = cardInventory.getCardByIndex(indexInList);
        returnedCard.performOnHero(this);
        returnedCard.performOnMonsters(this, allMonsters);

    }

    //EFFECTS: returns the initial of Hero's name
    public String getName() {
        return myName.substring(0, 1);
    }
}
