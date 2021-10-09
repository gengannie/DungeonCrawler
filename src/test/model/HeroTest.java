package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeroTest {
    Hero aHero;

    @BeforeEach
    void setUp() {
        aHero = new Hero(3, "testHero");
    }

    @Test
    void changeMoveTest(){
        int howManyMove = aHero.getMoveSquares();
        assertEquals(howManyMove, 3);
        aHero.changeMovePoints(1);
        assertEquals(aHero.getMoveSquares(), 4);


    }

    @Test
    void testRightReturns(){
        assertEquals(aHero.getName(), "t");
    }

    @Test
    void testHealAtMaxHealth() {
        aHero.heal(5);
        assertTrue(aHero.getCurrentHealth() == aHero.MAX_HEALTH);
    }

    @Test
    void testHealAtMinHealth() {
        aHero.getAttacked(aHero.MAX_HEALTH);
        assertTrue(aHero.getIsDead());
        aHero.heal(7);
        assertTrue(aHero.getCurrentHealth() == 7);
        assertFalse(aHero.getIsDead());
    }

    @Test
    void testHealNotAtMaxHealth() {
        aHero.getAttacked(aHero.MAX_HEALTH - 3);
        aHero.heal(7);
        if (3 < aHero.MAX_HEALTH) {
            assertTrue(aHero.getCurrentHealth() == aHero.MAX_HEALTH);
        }
    }

    @Test
    void testSmallHeal(){
        aHero.getAttacked(2);
        aHero.heal(1);
        assertTrue(aHero.getCurrentHealth()== aHero.MAX_HEALTH-1);
    }

    @Test
    void testLargeHeal() {
        aHero.heal(10);
        assertTrue(aHero.getCurrentHealth()== aHero.MAX_HEALTH);

    }

    @Test
    void moveHeroPos(){
        aHero.moveHero(2,3);
        assertTrue(aHero.getPosX() == 2);
        assertTrue(aHero.getPosY() == 3);
    }

    @Test
    void moveHeroPosMultTimes(){
        aHero.moveHero(2,3);
        assertTrue(aHero.getPosX() == 2);
        assertTrue(aHero.getPosY() == 3);
        aHero.moveHero(5,-3);
        assertTrue(aHero.getPosX() == 7);
        assertTrue(aHero.getPosY() == 0);
    }

    @Test
    void moveHeroTestNeg(){
        aHero.moveHero(-2,-3);
        assertTrue(aHero.getPosX() == 0);
        assertTrue(aHero.getPosY() == 0);

    }

}