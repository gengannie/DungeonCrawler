package ui;

import model.Hero;
import model.SmallMonsters;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.Scanner;

public class GameWorldPanel extends JComponent {
    private static final String JSON_STORE = "./data/gameworld.json";
    private static final String GAME_OVER = "Game Over!";
    private static final String REPLAY = "R to replay";
    private GameWorld gameOne;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Scanner scanner;
    private Hero mainCharacter;
    private int maxTurns;

    // MODIFIES: this, GameWorld
    // EFFECTS: initializes graphical interface panel of GameWorld,  sets size and background colour of panel,
    //	                                updates this with the game to be displayed
    public GameWorldPanel(GameWorld world) {
        scanner = new Scanner(System.in);
        gameOne = world;
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        setPreferredSize(new Dimension(gameOne.WIDTH, gameOne.HEIGHT));
        setBackground(Color.GRAY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawGame(g);

        if (gameOne.isGameOver()) {
            gameOver(g);
        }
    }

    // Draws the game
    // modifies: g
    // effects:  draws the game onto g
    private void drawGame(Graphics g) {
        drawMonsters(g);
        drawHero(g);
    }


    // modifies: g
    // effects:  draws the monsters onto g
    private void drawMonsters(Graphics g) {
        for (SmallMonsters next : gameOne.getMonsters()) {
            if (next.getIfInSight()) {
                drawInvader(g, next);
            }
        }
    }

    // Draw an invader
    // modifies: g
    // effects:  draws the invader i onto g
    private void drawInvader(Graphics g, SmallMonsters sm) {
        Color savedCol = g.getColor();
        g.setColor(new Color(14, 20, 120));
        g.fillOval(sm.getPosX(), sm.getPosY(), 60, 60);
        g.setColor(savedCol);
    }

    // Draw the tank
    // modifies: g
    // effects:  draws the tank onto g
    private void drawHero(Graphics g) {
        Hero h = gameOne.getHero();
        Color savedCol = g.getColor();
        g.setColor(new Color(210, 29, 29));
        g.fillRect(h.getPosX(), h.getPosY(), 60, 60);
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
