package ui.console;

import model.Event;
import model.EventLog;
import model.Hero;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.GameWorld;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

// Class to run GameWorld on console, this class also contains methods for json to read/write
public class GameWorldApp {
    private static final String JSON_STORE = "./data/gameworld.json";
    private GameWorld gameOne;
    private JsonReader jsonReader;
    private Scanner scanner;
    private Hero mainCharacter;
    private int maxTurns;

    // MODIFIES: this, GameWorld
    // EFFECTS: initializes GameWorld
    public GameWorldApp() throws FileNotFoundException {
        scanner = new Scanner(System.in);
        gameOne = new GameWorld(250);
        jsonReader = new JsonReader(JSON_STORE);
        runGameWorld();
    }

    // MODIFIES: this
    // EFFECTS: initializes GameWorld
    private void setUpWorld() {
        scanner = new Scanner(System.in);
        gameOne.displayIntroMessage();
        String nameOfHero = scanner.nextLine();
        mainCharacter = new Hero(1, nameOfHero);
        gameOne.addHeroToGame(mainCharacter);
        maxTurns = mainCharacter.getMaxTurns();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runGameWorld() {
        setUpWorld();
        int currentTurn = maxTurns;
        while (true) {
            while (currentTurn > 0) {
                gameOne.displayCurrWorld(currentTurn);
                gameOne.displayOptions(currentTurn);
                String input = scanner.nextLine();
                if (input.equals("4")) {
                    gameOne.saveGameWorld();
                } else if (input.equals("5")) {
                    loadGameWorld();
                } else {
                    iterations(input, gameOne);
                    currentTurn -= 1;
                }
            }
            gameOne.updateDeaths();
            gameOne.moveMonsters();
            currentTurn = maxTurns;
        }
    }

    //MODIFIES: this
    //EFFECTS: sets up game as a new game
    public void loadGame(GameWorld oldGame) {
        gameOne = oldGame;
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    public void loadGameWorld() {
        try {
            jsonReader = new JsonReader(JSON_STORE);
            loadGame(jsonReader.read());
            System.out.println("Loaded " + "recent game" + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //REQUIRES: input to be valid string
    //EFFECTS: iterates the various options the hero has until user terminates the game
    public void iterations(String input, GameWorld gameOne) {
        Scanner scanner = new Scanner(System.in);
        if (input.equals("QUIT")) {
            Iterator<Event> printLogs = EventLog.getInstance().iterator();
            while (printLogs.hasNext()) {
                System.out.println(printLogs.next());
            }
            System.exit(0);
        } else if (input.equals("1")) {
            gameOne.displayCardInfo();
            System.out.println("Which card do you want to use? Input the number associated with it");
            String whichCard = scanner.nextLine();
            int indexOfCard = Integer.parseInt(whichCard) - 1;
            gameOne.processCardBehavior(indexOfCard);
            gameOne.displayHeroStats();
        } else if (input.equals("2")) {
            gameOne.moveHero(1, 1);
        } else if (input.equals("3")) {
            gameOne.attackMonsters();
        }
        gameOne.updateDeaths();

    }
}
