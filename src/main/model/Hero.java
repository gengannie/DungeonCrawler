package model;

import java.util.ArrayList;

// Main character of this game
public class Hero {
    private static final int NUM_OF_TURNS = 2;
    public static final int VISIBLE = 2;
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
        hitPoints = 3;
        isDead = false;

    }

    //MODIFIES: this
    //EFFECTS: updates mana bar, if exceeds FULL_MANA, a new card is placed in inventory and returns updated mana
    public int updateManaBar(int points) {
        manaBar += points;
        if (manaBar >= FULL_MANA) {
            manaBar -= FULL_MANA;
            cardInventory.getNewStunCard();
        }
        return manaBar;

    }

    // MODIFIES: this
    //EFFECTS: sets health and mana to desired number for saving purposes
    public void setHealthAndMana(int health, int mana) {
        this.health = health;
        this.manaBar = mana;
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

    //MODIFIES: this
    //EFFECTS: if health <= 0, set isDead to true
    public void setIsDead() {
        if (health <= 0) {
            isDead = true;
        } else {
            isDead = false;
        }
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
        if (x > moveSquares) {
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

    //EFFECTS: returns card description of cards in inventory

    public ArrayList<String> getCardDes() {
        return cardInventory.getCardsDescription();
    }

    //REQUIRES: cardInventory to be not empty
    //MODIFIES: this
    //EFFECTS: modifies hero attributes according to behavior of card
    public void useCard(int indexInList, ArrayList<SmallMonsters> allMonsters) {
        Cards returnedCard = cardInventory.getCardByIndex(indexInList);
        if (returnedCard.getNameOfCard().equals("Healing Potion")) {
            returnedCard.performOnHero(this);
        } else {
            returnedCard.performOnMonsters(this, allMonsters);
        }


    }

    //EFFECTS: returns the initial of Hero's name
    public String getName() {
        return myName.substring(0, 1);
    }

    //MODIFIES: this
    //EFFECTS: adds new stun card to inventory for saving purposes
    public void getStunCard() {
        cardInventory.getNewStunCard();
    }

    //MODIFIES: this
    //EFFECTS: adds new healing potion to inventory for saving purposes
    public void getHealingPot() {
        cardInventory.getNewHealingPot();
    }

    //MODIFIES: this
    //EFFECTS: sets cardinventory to be empty
    public void resetCardInventory() {
        cardInventory.resetInventory();
    }

}
