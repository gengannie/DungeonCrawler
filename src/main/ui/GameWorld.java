package ui;

import model.Hero;
import model.Rat;
import model.SmallMonsters;

import java.util.ArrayList;
import java.util.Random;

// Represents the model and behavior of a console user-interface
// lays out basic world construction
public class GameWorld {
    protected static final int SQUARE_DIM = 20;
    protected int numOfMonsters;
    protected int numOfChest;
    protected int numOfFountain;
    protected int[][] worldGrid;
    protected ArrayList<SmallMonsters> allMonsters;
    protected boolean canAttack;


    //EFFECTS: initialize world interface
    public GameWorld(int numOfMonsters) {
        this.numOfMonsters = numOfMonsters;
        numOfChest = 2;
        numOfFountain = 1;
        worldGrid = new int[SQUARE_DIM][SQUARE_DIM];
        allMonsters = new ArrayList<>();
        placeMonstersInWorld(numOfMonsters);
        canAttack = false;

    }
    //MODIFIES: this
    //EFFECTS: selects a random place to insert a monster
    // 1 is monster

    public void placeMonstersInWorld(int numOfMonsters) {
        Random seed = new Random();
        for (int i = 0; i < numOfMonsters; i++) {
            boolean identicalPos = true;
            while (true) {
                int randX = (seed.nextInt(SQUARE_DIM));
                int randY = (seed.nextInt(SQUARE_DIM));
                if (worldGrid[randX][randY] == 0 && (randX != 0 && randY != 0)) {
                    worldGrid[randX][randY] = 1;
                    SmallMonsters addRat = new Rat(randX, randY);
                    allMonsters.add(addRat);
                    break;
                }
            }
        }

    }

    public void displayIntroMessage() {
        System.out.println("Welcome to the game, please input your hero's name :)");
    }

    public void testTHis() {
        for (SmallMonsters m : allMonsters) {
            if (m.getIfInSight()) {
                System.out.println(m.getName() + " is at " + m.getPosX() + " " + m.getPosY());
            }
        }
    }

    public void displayCurrWorld(Hero h, int turns) {
        for (int i = h.getPosX(); i < h.VISIBLE + h.getPosX(); i++) {
            for (int j = h.getPosY(); j < h.VISIBLE + h.getPosY(); j++) {
                if (i == h.getPosX() && j == h.getPosY()) {
                    System.out.print(h.getName());
                } else if (worldGrid[i][j] == 1) {
                    for (SmallMonsters m : allMonsters) {
                        if (m.getPosX() == i && m.getPosY() == j) {
                            System.out.print(m.getName() + " " + m.getHealth());
                            if (m.getIfInSight() == false) {
                                m.changeThisSight();
                            }
                            canAttack = true;
                            break;
                        }
                    }
                } else {
                    System.out.print("_");
                }
            }
            System.out.println();
        }
        displayOptions(h, turns);
    }

    //TODO: 1. seems pretty done v
    //TODO: movement is bad v
    //TODO: no world objects to interact
    //TODO: 4. attack monsters! v

    //EFFECTS: displays what the user can do, corresponding to a integer
    public void displayOptions(Hero h, int turns) {
        System.out.println("You have " + turns + " turns left before the monsters attack");
        System.out.println("Input QUIT to quit game");
        System.out.println("You can choose one of the following per turn. Input the number associated with the action");
        System.out.println("1. Check and use cards");
        System.out.println("2. Move left, right, up, or down");
        System.out.println("3. Interact with world objects");
        System.out.println("4. Attack monsters!");
        displayHeroStats(h);

    }

    // TODO
    public void displayCardInfo(Hero h) {
        ArrayList<String> returnedCardList = h.getCardDes();
        int currInd = 1;
        for (int i = 0; i < returnedCardList.size(); i += 2) {
            System.out.print(currInd + ": " + returnedCardList.get(i));
            System.out.print(" : ");
            System.out.println(returnedCardList.get(i + 1));
            currInd += 1;
        }

    }

    // TODO: they can't really move, and the conditions to attack are unclear
    ///TODO: find a way to update the state of monsters
    //MODIFIES: this
    //EFFECTS: updates monster position in world, monster moves, and hero health might decrease
    public void moveMonsters(Hero h) {
        for (SmallMonsters m : allMonsters) {
            if (m.getIfInSight() && m.getCanMove() == true) {
                m.attack(h);
            }
        }

    }

    //EFFECTS: show Hero's updated health, move, and action points
    public void displayHeroStats(Hero h) {
        System.out.println("Health: " + h.getCurrentHealth());
        System.out.println("Attack: " + h.getHitPoints());
        System.out.println("Movement: " + h.getMoveSquares());

    }

    //EFFECTS: returns whether or not there are monsters to attack
    public boolean canAttackMonsters() {
        return canAttack;

    }

    public void processCardBehavior(Hero h, int ind) {
        h.useCard(ind, allMonsters);
    }

    // MODIFIES: this, Hero
    // EFFECTS: remove Hero or monsters from the world if health is <= 0
    public void updateDeaths(Hero h) {
        if (h.getIsDead()) {
            gameOver();
        }
    }

    //EFFECTS: terminates program if hero has died
    public void gameOver() {
        System.out.println("Sorry, your hero has died :(");
        System.exit(-1);
    }

    //MODIFIES: this
    //EFFECTS: removes this monster from display if dead
    public void removeFromWorldGrid(SmallMonsters sm) {
        worldGrid[sm.getPosX()][sm.getPosY()] = 0;

    }

    // MODIFIES: this
    // EFFECTS: Hero hits a random monster nearby that he can see
    public void attackMonsters(Hero h) {
        if (canAttack) {
            for (SmallMonsters sm : allMonsters) {
                if (sm.getIfInSight() && sm.getIsDead() == false) {
                    sm.getHit(h.getHitPoints());
                    System.out.println("You just hit this" + sm.getName());
                    if (sm.getIsDead()) {
                        allMonsters.remove(sm);
                        System.out.println(sm.getName() + "Just died!");
                        removeFromWorldGrid(sm);
                    }
                    h.updateManaBar(h.getHitPoints());
                    break;
                }
            }
        }
    }
}
