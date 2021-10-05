package ui;

import model.Hero;
import model.Rat;
import model.SmallMonsters;

import java.util.ArrayList;
import java.util.Random;

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
            while (identicalPos) {
                int randX = (seed.nextInt(SQUARE_DIM));
                int randY = (seed.nextInt(SQUARE_DIM));
                if (worldGrid[randX][randY] == 0 && (randX != 0 && randY != 0)) {
                    identicalPos = false;
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

    //TODO
    public void displayWorld(Hero h, int turns) {
        int displayDimensions = h.VISIBLE;
        int startInd = 0;
        int startX = h.getPosX();
        int startY = h.getPosY();
        for (int i = startX; i < displayDimensions + startX; i++) {
            for (int j = startY; j < displayDimensions + startY; j++) {
                if (i == startX && j == startX) {
                    System.out.print(h.getName());
                } else if (worldGrid[i][j] == 1) {
                    SmallMonsters result = allMonsters.get(startInd);
                    System.out.print(result.getName());
                    startInd += 1;
                    canAttack = true;
                    result.changeThisSight();
                } else {
                    System.out.print("_");
                }
            }
            System.out.println();
        }
        displayOptions(h, turns);
        displayHeroStats(h);


    }

    //TODO: 1. seems pretty done
    //TODO: movement is bad
    //TODO: no world objects to interact
    //TODO: 4. attack monsters!

    //EFFECTS: displays what the user can do, corresponding to a integer
    public void displayOptions(Hero h, int turns) {
        System.out.println("You have " + turns + " turns left before the monsters attack");
        System.out.println("Input QUIT to quit game");
        System.out.println("You can choose one of the following per turn. Input the number associated with the action");
        System.out.println("1. Check and use cards");
        System.out.println("2. Move left, right, up, or down");
        System.out.println("3. Interact with world objects");
        System.out.println("4. Attack monsters!");

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
}
