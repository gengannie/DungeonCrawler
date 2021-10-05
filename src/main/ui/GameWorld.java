package ui;

public class GameWorld {
    protected static final int SQUARE_DIM = 10;
    protected int numOfMonsters;
    protected int numOfChest;
    protected int numOfFountain;

    //EFFECTS: initialize world interface
    public GameWorld(int numOfMonsters) {
        this.numOfMonsters = numOfMonsters;
        numOfChest = 2;
        numOfFountain = 1;
    }
}
