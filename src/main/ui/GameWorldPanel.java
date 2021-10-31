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
import java.util.Scanner;
import java.lang.*;

public class GameWorldPanel extends JComponent {
    private static final String JSON_STORE = "./data/gameworld.json";
    final BufferedImage image = ImageIO.read(new File("./data/hero.png"));
    private static final String GAME_OVER = "Game Over!";
    private static final String REPLAY = "R to replay";
    private int heroPosX;
    private int heroPosY;

    private static int WORLD_BLOCK;
    private GameWorld gameOne;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Scanner scanner;
    private Hero mainCharacter;
    private int maxTurns;

    // MODIFIES: this, GameWorld
    // EFFECTS: initializes graphical interface panel of GameWorld,  sets size and background colour of panel,
    //	                                updates this with the game to be displayed
    public GameWorldPanel(GameWorld world) throws IOException {
        scanner = new Scanner(System.in);
        gameOne = world;
        heroPosX = gameOne.WIDTH / 2;
        heroPosY = gameOne.HEIGHT / 2;
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        setPreferredSize(new Dimension(gameOne.WIDTH, gameOne.HEIGHT));
        setBackground(Color.GRAY);
        WORLD_BLOCK = gameOne.WIDTH / (gameOne.getHero().VISIBLE * 2 + 1);
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
        drawMonsters(g);
        drawHero(g);
    }


    // modifies: g
    // effects:  draws the monsters onto g
    private void drawMonsters(Graphics g) throws IOException {
        for (SmallMonsters next : gameOne.getMonsters()) {
            if (next.getIfInSight()) {
                drawInvader(g, next);
            }
        }
    }

    //EFFECTS: returns a position corresponding to world canvas
    private int calculatePosX(int pos) {
        Hero h = gameOne.getHero();
        int heroPos = h.getPosX();
        int x = Math.abs(heroPos - pos);
        return x * WORLD_BLOCK;
    }

    //EFFECTS: returns a position corresponding to world canvas
    private int calculatePosY(int pos) {
        Hero h = gameOne.getHero();
        int heroPos = h.getPosY();
        int x = Math.abs(heroPos - pos);
        return x * WORLD_BLOCK;
    }

    // Draw an invader
    // modifies: g
    // effects:  draws the invader i onto g
    private void drawInvader(Graphics g, SmallMonsters sm) throws IOException {
        Color savedCol = g.getColor();

        g.drawImage(image, calculatePosX(sm.getPosX()), calculatePosY(sm.getPosY()), null);
        g.setColor(savedCol);
    }

    // Draw the tank
    // modifies: g
    // effects:  draws the tank onto g
    private void drawHero(Graphics g) {

        Color savedCol = g.getColor();
        g.setColor(new Color(210, 29, 29));
        g.fillRect(heroPosX * WORLD_BLOCK, heroPosY * WORLD_BLOCK, WORLD_BLOCK, WORLD_BLOCK);
        g.setColor(savedCol);
    }

    // Draws the "game over" message and replay instructions
    // modifies: g
    // effects:  draws "game over" and replay instructions onto g
    private void gameOver(Graphics g) {
        Color saved = g.getColor();
        g.setColor(new Color(0, 0, 0));
        g.setFont(new Font("Arial", 20, 20));
        FontMetrics fm = g.getFontMetrics();
        centreString(GAME_OVER, g, fm, gameOne.HEIGHT / 2);
        centreString(REPLAY, g, fm, gameOne.HEIGHT / 2 + 50);
        g.setColor(saved);
    }

    // Centres a string on the screen
    // modifies: g
    // effects:  centres the string str horizontally onto g at vertical position yPos
    private void centreString(String str, Graphics g, FontMetrics fm, int posY) {
        int width = fm.stringWidth(str);
        g.drawString(str, (gameOne.WIDTH - width) / 2, posY);
    }
}
