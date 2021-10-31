package ui;

import model.Hero;
import model.SmallMonsters;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


// This class references code from CPSC210/B02-SpaceInvadersBase
// Link: https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase

// JComponent class that "paints" world objects
public class GameWorldPanel extends JComponent {
    private static final String JSON_STORE = "./data/gameworld.json";
    private static final int LIMIT = 2;
    final BufferedImage image = ImageIO.read(new File("./data/hero.png"));
    private static final String GAME_OVER = "Game Over!";
    private static final String REPLAY = "R to replay";
    private static int CARD_POS = 0;
    private int heroPosX;
    private int heroPosY;

    private static int WORLD_BLOCK;
    private GameWorld gameOne;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Scanner scanner;
    private int posInList = 0;

    // MODIFIES: this, GameWorld
    // EFFECTS: initializes graphical interface panel of GameWorld,  sets size and background colour of panel,
    //	                                updates this with the game to be displayed
    public GameWorldPanel(GameWorld world) throws IOException {
        scanner = new Scanner(System.in);
        gameOne = world;
        heroPosX = (GameWorld.WIDTH - 150) / 2;
        heroPosY = (GameWorld.HEIGHT - 400) / 2;
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        setPreferredSize(new Dimension(GameWorld.WIDTH, GameWorld.HEIGHT));
        setBackground(Color.GRAY);
        WORLD_BLOCK = ((GameWorld.WIDTH - 600) / (gameOne.getHero().VISIBLE * 2 + 1));
        CARD_POS = GameWorld.HEIGHT - WORLD_BLOCK;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            drawGame(g);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (gameOne.isGameOver()) {
            gameOver(g);
        }
    }

    // Draws the game
    // modifies: g
    // effects:  draws the game onto g
    private void drawGame(Graphics g) throws IOException {
        drawHero(g);
        drawMonsters(g);
        drawCards(g);
    }


    // modifies: g
    // effects:  draws the monsters onto g
    private void drawMonsters(Graphics g) throws IOException {
        for (SmallMonsters next : gameOne.getMonsters()) {
            if (next.getIfInSight()) {
                drawMonster(g, next);
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: increments position in card slider
    public void incrementPos() {
        posInList += 1;
        if (posInList >= gameOne.getHero().getCardDes().size() - 1) {
            posInList = 0;
        }
    }

    //MODIFIES: g
    //EFFECTS: draws the arraylist of string description (corresponding to card list) onto the world
    private void drawCards(Graphics g) {
        ArrayList<String> cardDes = gameOne.getHero().getCardDes();
        int count = 1;
        while (count <= LIMIT) {
            for (int i = posInList; i < cardDes.size(); i += 2) {
                drawCard(cardDes.get(i), cardDes.get(i + 1), g, count);
                count += 1;
            }
        }
    }

    //MODIFIES: g
    //EFFECTS: draws each individual card onto the world coponent
    private void drawCard(String name, String des, Graphics g, int index) {
        Color savedCol = g.getColor();

        if (name.equals("Healing Potion")) {
            g.setColor(new Color(219, 169, 191));
        } else {
            g.setColor(new Color(83, 18, 47));
        }
        g.fillRect(index * WORLD_BLOCK, CARD_POS, WORLD_BLOCK, WORLD_BLOCK);

        //g.drawImage(image, calculatePosX(sm.getPosX()), calculatePosY(sm.getPosY()), null);
        g.setColor(savedCol);

    }

    //EFFECTS: returns a position corresponding to world canvas
    private int calculatePosX(int pos) {
        Hero h = gameOne.getHero();
        int heroPos = h.getPosX();
        int x = pos - heroPos;
        return (x * WORLD_BLOCK) + heroPosX;
    }

    //EFFECTS: returns a position corresponding to world canvas
    private int calculatePosY(int pos) {
        Hero h = gameOne.getHero();
        int heroPos = h.getPosY();
        int x = pos - heroPos;
        return (x * WORLD_BLOCK) + heroPosY;
    }

    // MODIFIES: g
    // EFFECTS:  draws the SmallMonster sm onto g
    private void drawMonster(Graphics g, SmallMonsters sm) throws IOException {
        Color savedCol = g.getColor();

        g.setColor(new Color(159, 111, 194));
        g.fillOval(calculatePosX(sm.getPosX()), calculatePosY(sm.getPosY()), WORLD_BLOCK, WORLD_BLOCK);
        //g.drawImage(image, calculatePosX(sm.getPosX()), calculatePosY(sm.getPosY()), null);
        g.setColor(savedCol);
    }

    // MODIFIES: g
    // EFFECTS:  draws the hero onto g on a fixed location
    private void drawHero(Graphics g) {
        Color savedCol = g.getColor();
        g.setColor(new Color(210, 29, 29));
        g.fillRect(heroPosX, heroPosY, WORLD_BLOCK, WORLD_BLOCK);
        g.setColor(savedCol);
    }

    // Draws the "game over" message and replay instructions
    // modifies: g
    // effects:  draws "game over" and replay instructions onto g
    private void gameOver(Graphics g) {
        Color saved = g.getColor();
        g.setColor(new Color(0, 0, 0));
        g.setFont(new Font("Serif", 200, 200));
        FontMetrics fm = g.getFontMetrics();
        centreString(GAME_OVER, g, fm, gameOne.HEIGHT / 2);
        g.setColor(saved);
        System.exit(0);
    }

    // Centres a string on the screen
    // modifies: g
    // effects:  centres the string str horizontally onto g at vertical position yPos
    private void centreString(String str, Graphics g, FontMetrics fm, int posY) {
        int width = fm.stringWidth(str);
        g.drawString(str, (gameOne.WIDTH - width) / 2, posY);
    }
}
