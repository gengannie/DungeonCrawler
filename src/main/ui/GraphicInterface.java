package ui;

import model.Hero;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Scanner;

public class GraphicInterface extends JFrame {
    private static final String JSON_STORE = "./data/gameworld.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private GameWorld gameWorld;
    private GameWorldPanel gamePanel;
    private HeroStatsPanel heroStats;
    private Scanner scanner;
    private Hero mainCharacter;
    private int maxTurns;
    private int currentTurn;


    // Constructs main window
    // effects: sets up window in which Space Invaders game will be played
    public GraphicInterface(String nameOfHero) throws IOException {
        super("Dungeon Crawler: Too Many Rats Edition");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        gameWorld = new GameWorld(250);
        gameWorld.addHeroToGame(new Hero(3, nameOfHero));
        maxTurns = gameWorld.getHero().getMaxTurns();
        gameWorld.updateMonsterSight();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        gamePanel = new GameWorldPanel(gameWorld);
        heroStats = new HeroStatsPanel(gameWorld);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);

        add(gamePanel);
        add(heroStats, BorderLayout.NORTH);
        addKeyListener(new KeyHandler());
        currentTurn = maxTurns;

        pack();
        centreOnScreen();
        setVisible(true);
    }


    //MODIFIES: this
    //EFFECTS: sets up game as a new game
    public void loadGame(GameWorld oldGame) {
        gameWorld = oldGame;
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

    // key handler
    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            processCommand(e.getKeyCode());
        }
    }


    private void updateWorld() {
        currentTurn -= 1;
        if (currentTurn == 0) {
            currentTurn = maxTurns;
        }
        gameWorld.update();
        gamePanel.repaint();
        heroStats.update();
    }

    // Responds to key press codes
    // MODIFIES: this
    // EFFECTS: //TODO
    // https://www.programcreek.com/java-api-examples/?class=java.awt.event.KeyEvent&method=VK_R
    public void processCommand(int keyCode) {
        switch (keyCode) {
            case (KeyEvent.VK_1):
                //display cards
                break;
            case (KeyEvent.VK_2):
                //move
                gameWorld.moveHero(1, 1);
                break;
            case (KeyEvent.VK_3):
                //attack monsters
                gameWorld.attackMonsters();
                break;
            case (KeyEvent.VK_S):
                gameWorld.saveGameWorld();
                break;
            case (KeyEvent.VK_L):
                //load game
                loadGameWorld();
                break;
            case (KeyEvent.VK_Q):
                //quit game
                gameWorld.setGameOver();
                break;
        }
        updateWorld();
    }

    // Centres frame on desktop
    // modifies: this
    // effects:  location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - getWidth()) / 2, (screen.height - getHeight()) / 2);
    }


}
