package ui;

import model.Hero;
import model.Rat;
import model.SmallMonsters;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.JsonWriter;
import persistence.Write;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

// Represents the model and behavior of a console user-interface
// lays out basic world construction
public class GameWorld implements Write {
    private static final String JSON_STORE = "./data/gameworld.json";
    public static final int WIDTH = 1600;
    public static final int HEIGHT = 1000;
    protected static final int SQUARE_DIM = 20;
    protected int numOfMonsters;
    protected int[][] worldGrid;
    protected ArrayList<SmallMonsters> allMonsters;
    protected boolean canAttack;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Hero hero;
    private boolean isGameOver;


    //EFFECTS: initialize world interface
    public GameWorld(int numOfMonsters) {
        this.numOfMonsters = numOfMonsters;
        worldGrid = new int[SQUARE_DIM][SQUARE_DIM];
        jsonReader = new JsonReader(JSON_STORE);
        allMonsters = new ArrayList<>();
        placeMonstersInWorld(numOfMonsters);
        canAttack = false;
        jsonWriter = new JsonWriter(JSON_STORE);
        isGameOver = false;

    }

    //MODIFIES: this
    //EFFECTS: adds hero to this game world for saving purposes
    public void addHeroToGame(Hero h) {
        this.hero = h;
    }

    //EFFECTS: returns hero for saving/ testing purposes
    public Hero getHero() {
        return hero;
    }

    public void moveHero(int x, int y) {
        hero.moveHero(x, y);
    }

    //MODIFIES: this
    //EFFECTS: replaces this world grid with a new one for saving purposes
    public void loadWorldGrid(int[][] arr) {
        worldGrid = arr;
    }

    //EFFECTS: returns this world grid with a new one for saving purposes
    public int[][] returnWorldGrid() {
        return worldGrid;
    }

    //MODIFIES: this
    //EFFECTS: replaces this new monsters arraylist for saving purposes
    public void loadMonsters(ArrayList<SmallMonsters> allMonsters) {
        this.allMonsters = allMonsters;
    }

    //EFFECTS: returns this new monsters arraylist for saving purposes
    public ArrayList<SmallMonsters> getMonsters() {
        return allMonsters;
    }

    //MODIFIES: this
    //EFFECTS: selects a random place to insert a monster, where 1 is a monster
    public void placeMonstersInWorld(int numOfMonsters) {
        Random seed = new Random();
        for (int i = 0; i < numOfMonsters; i++) {
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

    //EFFECTS: updates world by moving monsters and updating deaths
    public void update() {
        updateMonsterSight();
        updateDeaths();
    }

    // EFFECTS: display initial message to user
    public void displayIntroMessage() {
        System.out.println("Welcome to the game, please input your hero's name :)");
    }

    public void updateMonsterSight() {
        for (int i = hero.getPosX() - hero.VISIBLE; i <= hero.VISIBLE + hero.getPosX(); i++) {
            for (int j = hero.getPosY() - hero.VISIBLE; j <= hero.VISIBLE + hero.getPosY(); j++) {
                if ((i >= 0 && j >= 0) && worldGrid[i][j] == 1) {
                    for (SmallMonsters m : allMonsters) {
                        if (m.getPosX() == i && m.getPosY() == j) {
                            if (m.getIfInSight() == false) {
                                m.changeThisSight();
                            }
                            canAttack = true;
                            break;
                        }
                    }
                }
            }
        }
    }

    // EFFECTS: displays the 2D array grid of the "world"
    public void displayCurrWorld(int turns) {
        for (int i = hero.getPosX() - hero.VISIBLE; i <= hero.VISIBLE + hero.getPosX(); i++) {
            for (int j = hero.getPosY() - hero.VISIBLE; j <= hero.VISIBLE + hero.getPosY(); j++) {
                if (i == hero.getPosX() && j == hero.getPosY()) {
                    System.out.print(hero.getName());
                } else if (!posInBounds(i, j)) {
                    System.exit(0);
                } else if ((i >= 0 && j >= 0) && worldGrid[i][j] == 1) {
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
    }

    //EFFECTS: checks whether or not current field of view is in bounds or not
    public boolean posInBounds(int i, int j) {
        if (i >= SQUARE_DIM || j >= SQUARE_DIM) {
            System.out.println("You've reached the end of the world :)");
            return false;
        }
        return true;
    }

    //EFFECTS: displays what the user can do, corresponding to a integer
    public void displayOptions(int turns) {
        System.out.println("You have " + turns + " turns left before the monsters attack");
        System.out.println("Input QUIT to quit game");
        System.out.println("You can choose one of the following per turn. Input the number associated with the action");
        System.out.println("1. Check and use cards");
        System.out.println("2. Move left, right, up, or down");
        System.out.println("3. Attack monsters!");
        System.out.println("4. Save game");
        System.out.println("5. Load game");
        displayHeroStats();

    }

    //EFFECTS: returns whether or not game is over
    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver() {
        isGameOver = true;
    }


    //EFFECTS: displays the current card inventory of hero
    public void displayCardInfo() {
        ArrayList<String> returnedCardList = hero.getCardDes();
        int currInd = 1;
        for (int i = 0; i < returnedCardList.size(); i += 2) {
            System.out.print(currInd + ": " + returnedCardList.get(i));
            System.out.print(" : ");
            System.out.println(returnedCardList.get(i + 1));
            currInd += 1;
        }

    }

    //MODIFIES: this
    //EFFECTS: updates monster position in world, monster moves, and hero health might decrease
    public void moveMonsters() {
        for (SmallMonsters m : allMonsters) {
            if (m.getIfInSight() && m.getCanMove() == true && !m.getIsDead()) {
                m.attack(hero);
                System.out.println(hero.getCurrentHealth());
            }
        }

    }

    //EFFECTS: show Hero's updated health, move, and action points
    public void displayHeroStats() {
        System.out.println("Health: " + hero.getCurrentHealth());
        System.out.println("Attack: " + hero.getHitPoints());
        System.out.println("Movement: " + hero.getMoveSquares());

    }

    //EFFECTS: returns whether or not there are monsters to attack
    public boolean canAttackMonsters() {
        return canAttack;

    }

    //MODIFIES: Hero, SmallMonsters
    //EFFECTS: calls method in Cards class to perform card behavior on selected players
    public void processCardBehavior(int ind) {
        hero.useCard(ind, allMonsters);
    }

    // MODIFIES: this, Hero
    // EFFECTS: remove Hero or monsters from the world if health is <= 0
    public void updateDeaths() {
        if (hero.getIsDead()) {
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
    public void attackMonsters() {
        if (canAttack) {
            for (SmallMonsters sm : allMonsters) {
                if (sm.getIsDead() == true) {
                    allMonsters.remove(sm);
                    removeFromWorldGrid(sm);
                } else if (sm.getIfInSight() && sm.getIsDead() == false) {
                    sm.getHit(hero.getHitPoints());
                    System.out.println("You just hit this " + sm.getName());
                    if (sm.getIsDead()) {
                        allMonsters.remove(sm);
                        System.out.println("A rat just died!");
                        removeFromWorldGrid(sm);
                    }
                    hero.updateManaBar(hero.getHitPoints());
                    break;
                }
            }
        }
    }

    //EFFECTS: converts GameWorld data to json objects
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("worldGrid", worldGrid);
        json.put("monsters", allMonsters);
        json.put("Hero Health", hero.getCurrentHealth());
        json.put("Name", hero.getName());
        json.put("Mana", hero.getManaBar());
        json.put("Cards", hero.getCardDes());
        json.put("Move", hero.getMoveSquares());
        json.put("PosX", hero.getPosX());
        json.put("PosY", hero.getPosY());
        json.put("Hero isDead", hero.getIsDead());
        return json;
    }

    // EFFECTS: saves the workroom to file
    public void saveGameWorld() {
        try {
            jsonWriter.open();
            jsonWriter.write(this);
            jsonWriter.close();
            System.out.println("Saved your recent game to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }


}
