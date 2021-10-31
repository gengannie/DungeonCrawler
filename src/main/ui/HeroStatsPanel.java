package ui;

import model.Hero;

import javax.swing.*;
import java.awt.*;

// JPanel to display hero statistics (health, attack points, and so on as separate JLabel objects)
public class HeroStatsPanel extends JPanel {
    private GameWorld gameWorld;
    private static final String FONT_USED = Font.DIALOG_INPUT;
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
        construct();

    }

    //MODIFIES: this
    //EFFECTS: reconstructs the JLabel objects according to corresponding values
    private void construct() {
        heroHealthLbl = new JLabel(HEALTH_TXT + hero.getCurrentHealth());
        heroHealthLbl.setFont(new Font(FONT_USED, Font.BOLD, 50));
        heroManaLbl = new JLabel(MANA_TXT + hero.getManaBar());
        heroManaLbl.setFont(new Font(FONT_USED, Font.BOLD, 50));
        heroAttackLbl = new JLabel(ATTACK_TXT + hero.getHitPoints());
        heroAttackLbl.setFont(new Font(FONT_USED, Font.BOLD, 50));
        add(heroHealthLbl);
        add(Box.createHorizontalStrut(100));
        add(heroManaLbl);
        add(Box.createHorizontalStrut(100));
        add(heroAttackLbl);
    }

    // Updates the stats panel
    // MODIFIES: this
    // EFFECTS: reconstructs the world and then replaces them onto visual surface
    public void update() {
        construct();
        repaint();
    }
}
