package ui;

import model.Hero;

import javax.swing.*;
import java.awt.*;

public class HeroStatsPanel extends JPanel {
    private GameWorld gameWorld;
    private static final String HEALTH_TXT = "Health: ";
    private static final String MANA_TXT = "Mana: ";
    private static final String ATTACK_TXT = "Attack: ";
    private static final int LBL_WIDTH = 200;
    private static final int LBL_HEIGHT = 100;
    private JLabel heroHealthLbl;
    private JLabel heroManaLbl;
    private JLabel heroAttackLbl;
    private Hero hero;


    // constructs hero stats panel
    //EFFECTS: sets the background color, draws initial label
    public HeroStatsPanel(GameWorld world) {
        gameWorld = world;
        setBackground(new Color(124, 130, 199));
        this.hero = gameWorld.getHero();
        heroHealthLbl = new JLabel(HEALTH_TXT + hero.getCurrentHealth());
        heroHealthLbl.setFont(new Font("Calibri", Font.BOLD, 50));
        heroManaLbl = new JLabel(MANA_TXT + hero.getManaBar());
        heroManaLbl.setFont(new Font("Calibri", Font.BOLD, 50));
        heroAttackLbl = new JLabel(ATTACK_TXT + hero.getHitPoints());
        heroAttackLbl.setFont(new Font("Calibri", Font.BOLD, 50));
        add(heroHealthLbl);
        add(Box.createHorizontalStrut(100));
        add(heroManaLbl);
        add(Box.createHorizontalStrut(100));
        add(heroAttackLbl);
    }

    // Updates the score panel
    // modifies: this
    // effects:  updates number of invaders shot and number of missiles
    //           remaining to reflect current state of game
    public void update() {
        heroHealthLbl = new JLabel(HEALTH_TXT + hero.getCurrentHealth());
        heroManaLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        heroAttackLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        repaint();
    }
}
