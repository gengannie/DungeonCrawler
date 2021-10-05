package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

class HeroTest {
    Hero aHero;
    @BeforeEach
    void setUp(){
         aHero = new Hero(3,"testHero");
    }

    @Test
    void testHealAtMaxHealth(){
        assertTrue(aHero.heal(5) == aHero.MAX_HEALTH);
        assertTrue(aHero.getCurrentHealth() == aHero.MAX_HEALTH);
    }
    @Test
    void testHealAtMinHealth(){
        aHero.getAttacked(aHero.MAX_HEALTH);
        assertTrue(aHero.heal(7) == 7);
        assertTrue(aHero.getCurrentHealth() == 7);
    }
    @Test
    void testHealNotAtMaxHealth(){
        aHero.getAttacked(aHero.MAX_HEALTH-3);
        assertTrue(aHero.heal(7) == 7);
        if (3 < aHero.MAX_HEALTH){
            assertTrue(aHero.getCurrentHealth() == 3);
        }
    }

}