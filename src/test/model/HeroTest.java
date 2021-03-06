package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// Tests for Hero class
class HeroTest {
    Hero aHero;

    @BeforeEach
    void setUp() {
        aHero = new Hero(3, "testHero");
    }

    @Test
    void updateMan() {
        aHero.updateManaBar(4, 0);
        assertEquals(aHero.getManaBar(), 4);
    }

    @Test
    void updateToMaxMana(){
        aHero.updateManaBar(10,0);
        assertEquals(aHero.getManaBar(), 0);
        assertEquals(aHero.getCardDes().size() / 2, 3);
        assertEquals(aHero.getCardInventoryLength(), 3);
        aHero.updateManaBar(10,1);
        assertEquals(aHero.getManaBar(), 0);
        assertEquals(aHero.getCardDes().size() / 2, 4);
        assertEquals(aHero.getCardInventoryLength(), 4);
    }

    @Test
    void updateToOverMaxMana(){
        aHero.updateManaBar(15,0);
        assertEquals(aHero.getManaBar(), 5);
        assertEquals(aHero.getCardDes().size() / 2, 3);
    }


    @Test
    void changeMoveTest() {
        int howManyMove = aHero.getMoveSquares();
        assertEquals(howManyMove, 3);
        aHero.changeMovePoints(1);
        assertEquals(aHero.getMoveSquares(), 4);

    }

    @Test
    void testRightReturns() {
        assertEquals(aHero.getName(), "t");
    }

    @Test
    void testSetIsDeadNotDead() {
        aHero.setIsDead();
        assertFalse(aHero.getIsDead());
    }

    @Test
    void testSetIsDeadWithChangedHealth() {
        aHero.setHealthAndMana(0,0);
        assertFalse(aHero.getIsDead());
        aHero.setIsDead();
        assertTrue(aHero.getIsDead());
    }

    @Test
    void resetInventory() {
        aHero.resetCardInventory();
        ArrayList<String> expected = new ArrayList<>();
        assertEquals(aHero.getCardDes(), expected);
    }

    @Test
    void resetInventoryAndAddStunCard() {
        aHero.resetCardInventory();
        ArrayList<String> expected = new ArrayList<>();
        assertEquals(aHero.getCardDes(), expected);

        aHero.getStunCard();
        Cards stunCard = new StunCard();
        expected.add(stunCard.getNameOfCard());
        expected.add(stunCard.getDescription());
        assertEquals(aHero.getCardDes(), expected);
    }

    @Test
    void resetInventoryAndAddHealing() {
        aHero.resetCardInventory();
        ArrayList<String> expected = new ArrayList<>();
        assertEquals(aHero.getCardDes(), expected);
        aHero.getHealingPot();
        Cards healingPot = new HealingPotion();
        expected.add(healingPot.getNameOfCard());
        expected.add(healingPot.getDescription());
        assertEquals(aHero.getCardDes(), expected);
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
    void testInvalidHeal() {
        aHero.getAttacked(aHero.MAX_HEALTH);
        assertTrue(aHero.getIsDead());
        aHero.heal(0);
        assertTrue(aHero.getCurrentHealth() == 0);
        assertTrue(aHero.getIsDead());

    }
    @Test
    void testSmallHeal() {
        aHero.getAttacked(2);
        aHero.heal(1);
        assertTrue(aHero.getCurrentHealth() == aHero.MAX_HEALTH - 1);
    }

    @Test
    void testLargeHeal() {
        aHero.heal(10);
        assertTrue(aHero.getCurrentHealth() == aHero.MAX_HEALTH);

    }

    @Test
    void moveHeroPos() {
        aHero.moveHero(2, 3);
        assertTrue(aHero.getPosX() == 2);
        assertTrue(aHero.getPosY() == 3);
    }

    @Test
    void moveHeroPosMultTimes() {
        aHero.moveHero(2, 3);
        assertTrue(aHero.getPosX() == 2);
        assertTrue(aHero.getPosY() == 3);
        aHero.moveHero(5, -3);
        assertTrue(aHero.getPosX() == 5);
        assertTrue(aHero.getPosY() == 0);
        aHero.moveHero(10,10);
        assertTrue(aHero.getPosX() == 8);
        assertTrue(aHero.getPosY() == 3);

    }

    @Test
    void moveHeroTestNeg() {
        aHero.moveHero(-2, -3);
        assertTrue(aHero.getPosX() == 0);
        assertTrue(aHero.getPosY() == 0);

    }
    @Test
    void getMaxTurns() {
        assertEquals(aHero.getMaxTurns(), 2);
    }

    @Test
    void getHitPoints() {
        assertEquals(aHero.getHitPoints(), 3);
    }

    @Test
    void useCardHeal() {
        int num = aHero.getCardInventoryLength();
        aHero.getAttacked(10);
        aHero.useCard(0, new ArrayList<>() );
        assertEquals(aHero.getCurrentHealth(), 7);
        assertEquals(num - 1, aHero.getCardInventoryLength());
    }

    @Test
    void useCardStun() {
        int num = aHero.getCardInventoryLength();
        ArrayList<SmallMonsters> smallMonsters = new ArrayList<>();
        SmallMonsters rat = new Rat(1,1);
        assertEquals(rat.getHealth(), 2);
        rat.changeThisSight();
        smallMonsters.add(rat);

        aHero.useCard(1, smallMonsters);
        assertEquals(rat.getHealth(), 1);
        assertEquals(num - 1, aHero.getCardInventoryLength());
    }


    @Test
    void notValidIndexUseCard() {
        aHero.resetCardInventory();
        aHero.getAttacked(5);
        aHero.useCard(-1, new ArrayList<>() );
        assertEquals(aHero.getCurrentHealth(), 5);
        aHero.useCard(10, new ArrayList<>() );
        assertEquals(aHero.getCurrentHealth(), 5);
    }

    @Test
    void notValidIndexUseCardWithFullInventory() {
        aHero.getAttacked(5);
        aHero.useCard(-1, new ArrayList<>() );
        assertEquals(aHero.getCurrentHealth(), 5);
        aHero.useCard(10, new ArrayList<>() );
        assertEquals(aHero.getCurrentHealth(), 5);
    }

}