package ui;

import model.Hero;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Scanner;

public class GraphicInterface extends JFrame {
    private static final String JSON_STORE = "./data/gameworld.json";
    private static final int INTERVAL = 10;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private GameWorld gameWorld;
    private GameWorldPanel gamePanel;
    private HeroStatsPanel heroStats;
    private Scanner scanner;
    private Hero mainCharacter;
    private int maxTurns;

    // Constructs main window
    // effects: sets up window in which Space Invaders game will be played
    public GraphicInterface() {
        super("Dungeon Crawler: Too Many Rats Edition");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        gameWorld = new GameWorld(250);
        setUpWorld();
        gamePanel = new GameWorldPanel(gameWorld);
        heroStats = new HeroStatsPanel(gameWorld);
        add(gamePanel);
        add(heroStats, BorderLayout.NORTH);
        addKeyListener(new KeyHandler());
        pack();
        centreOnScreen();
        setVisible(true);
    }

    // runs graphical world
    public static void main(String[] args) {
        new GraphicInterface();
    }


    // MODIFIES: this
    // EFFECTS: initializes GameWorld
    private void setUpWorld() {
        scanner = new Scanner(System.in);
        gameWorld.displayIntroMessage();
        String nameOfHero = scanner.nextLine();
        mainCharacter = new Hero(1, nameOfHero);
        gameWorld.addHeroToGame(mainCharacter);
        maxTurns = mainCharacter.getMaxTurns();
        gameWorld.updateMonsterSight();
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
                break;
            case (KeyEvent.VK_3):
                //attack monsters
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
        gameWorld.update();
        gamePanel.repaint();
        heroStats.update();
    }

    // Centres frame on desktop
    // modifies: this
    // effects:  location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - getWidth()) / 2, (screen.height - getHeight()) / 2);
    }



}
