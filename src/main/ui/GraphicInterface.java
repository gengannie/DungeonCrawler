package ui;

import model.Hero;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class GraphicInterface extends JFrame {
    private static final String JSON_STORE = "./data/gameworld.json";
    private JsonReader jsonReader;
    private GameWorld gameWorld;
    private GameWorldPanel gamePanel;
    private HeroStatsPanel heroStats;
    private Hero mainCharacter;
    private final int maxTurns;
    private int currentTurn;


    // Constructs main window
    // effects: sets up window in which Space Invaders game will be played
    public GraphicInterface(String nameOfHero) throws IOException {
        super("Dungeon Crawler: Too Many Rats Edition");
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
    public void loadGame(GameWorld oldGame) throws IOException {
        gameWorld = oldGame;
        gamePanel = new GameWorldPanel(gameWorld);
        heroStats = new HeroStatsPanel(gameWorld);
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


    private void updateThenRepaint() {
        gameWorld.update();
        heroStats.removeAll();
        heroStats.revalidate();
        heroStats.update();
        gamePanel.revalidate();
        gamePanel.repaint();
    }

    private void updateWorld() {
        currentTurn -= 1;
        if (currentTurn == 0) {
            currentTurn = maxTurns;
            gameWorld.moveMonsters();
            updateThenRepaint();
        }

    }

    // Responds to key press codes
    // MODIFIES: this
    // EFFECTS: //TODO
    // https://www.programcreek.com/java-api-examples/?class=java.awt.event.KeyEvent&method=VK_R
    public void processCommand(int keyCode) {
        updateWorld();
        switch (keyCode) {
            case (KeyEvent.VK_1):
                gameWorld.processCardBehavior(0);
                System.out.println("Used card");
                break;
            case (KeyEvent.VK_2):
                gameWorld.processCardBehavior(1);
                break;
            case (KeyEvent.VK_W):
                //move
                gameWorld.moveHero(1, 1);
                break;
            case (KeyEvent.VK_A):
                //attack monsters
                gameWorld.attackMonsters();
                break;
        }
        checkIfSaveOrLoad(keyCode);
        updateThenRepaint();

    }

    private void checkIfSaveOrLoad(int keyCode) {
        switch (keyCode) {
            case (KeyEvent.VK_E):
                //display cards
                gamePanel.incrementPos();
                break;
            case (KeyEvent.VK_S):
                gameWorld.saveGameWorld();
                break;
            case (KeyEvent.VK_D):
                loadGameWorld();
                break;
            case (KeyEvent.VK_Q):
                //quit game
                gameWorld.setGameOver();
                break;
        }

    }

    // Centres frame on desktop
    // modifies: this
    // effects:  location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - getWidth()) / 2, (screen.height - getHeight()) / 2);
    }


}
