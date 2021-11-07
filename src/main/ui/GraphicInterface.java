package ui;

import model.Hero;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

// extends JFrame, represents the composite of the visual interface
public class GraphicInterface extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/gameworld.json";
    private JsonReader jsonReader;
    private GameWorld gameWorld;
    private GameWorldPanel gamePanel;
    private HeroStatsPanel heroStats;
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
        Image icon = Toolkit.getDefaultToolkit().getImage("./data/hero.png");
        this.setIconImage(icon);
        JMenuBar menuBar = constructMenuBar();
        gamePanel = new GameWorldPanel(gameWorld);
        heroStats = new HeroStatsPanel(gameWorld);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);


        menuBar.setPreferredSize(new Dimension(GameWorld.WIDTH, 80));
        setJMenuBar(menuBar);

        add(gamePanel);
        add(heroStats, BorderLayout.NORTH);
        addKeyListener(new KeyHandler());
        currentTurn = maxTurns;
        pack();
        centreOnScreen();
        setVisible(true);
    }


    // This method references code from this online website
    // Link: https://www.geeksforgeeks.org/java-swing-jmenubar/
    private JMenuBar constructMenuBar() {
        Font font = new Font("Serif", Font.BOLD, 24);
        JMenu mainMenu = new JMenu("Options");
        mainMenu.setFont(font);
        JMenuItem instructions = new JMenuItem("Instructions");
        JMenuItem save = new JMenuItem("Save Game");
        JMenuItem load = new JMenuItem("Load Game");
        JMenuItem quit = new JMenuItem("Quit Game");

        instructions.addActionListener(this);
        save.addActionListener(this);
        load.addActionListener(this);
        quit.addActionListener(this);
        instructions.setFont(font);
        save.setFont(font);
        load.setFont(font);
        quit.setFont(font);

        mainMenu.add(instructions);
        mainMenu.add(save);
        mainMenu.add(load);
        mainMenu.add(quit);
        JMenuBar newBar = new JMenuBar();
        newBar.add(mainMenu);

        return newBar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        switch (s) {
            case ("Instructions"):
                break;
            case ("Save Game"):
                gameWorld.saveGameWorld();
                break;
            case ("Load Game"):
                loadGameWorld();
                break;
            case ("Quit Game"):
                gameWorld.setGameOver();
                break;
        }
        updateThenRepaint();
    }


    //MODIFIES: this
    //EFFECTS: sets up game as a new game
    public void loadGame(GameWorld oldGame) throws IOException {
        gameWorld = oldGame;
        gamePanel.loadNewGame(gameWorld);
        heroStats.loadNewGame(gameWorld);
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    public void loadGameWorld() {
        try {
            jsonReader = new JsonReader(JSON_STORE);
            loadGame(jsonReader.read());
            updateThenRepaint();
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


    //MODIFIES: this
    // EFFECTS: updates gameWorld then reconstructs world objects
    private void updateThenRepaint() {
        gameWorld.update();
        heroStats.removeAll();
        heroStats.revalidate();
        heroStats.update();
        gamePanel.revalidate();
        gamePanel.repaint();
    }

    //MODIFIES: this
    // EFFECTS: updates currentTurn, if currentTurn == 0 then set it to max turns and moveMonsters
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
    // EFFECTS: uses card
    // This method uses references code from this website:
    // https://www.programcreek.com/java-api-examples/?class=java.awt.event.KeyEvent&method=VK_R
    public void processCommand(int keyCode) {
        updateWorld();
        switch (keyCode) {
            case (KeyEvent.VK_1):
                gameWorld.processCardBehavior(0);
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


    //MODIFIES: this
    //EFFECTS: loads, saves, or quits the game according to key inputs
    private void checkIfSaveOrLoad(int keyCode) {
        switch (keyCode) {
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
