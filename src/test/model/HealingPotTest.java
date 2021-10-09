package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HealingPotTest {
    HealingPotion healP;
    Hero h;

    @BeforeEach
    void setUp(){
        healP = new HealingPotion();
        h = new Hero (1,"Test");
    }

    @Test
    void testBehav(){
        int currHealth = h.getCurrentHealth();
        h.getAttacked(5);
        healP.performOnHero(h);
        int expectedHealth = currHealth + healP.hitPoints ;
        if (expectedHealth> h.MAX_HEALTH) {
            expectedHealth = h.MAX_HEALTH;
        }
        assertEquals(h.getCurrentHealth(), expectedHealth);
    }
}
