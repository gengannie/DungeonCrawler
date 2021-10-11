package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Tests for SmallMonster class
public class SmallMonsterTest {
    SmallMonsters mons;
    @BeforeEach
    void setUp(){
        mons = new SmallMonsters(1,1,2,0,0);
    }

    @Test
    void getName() {
        assertEquals(mons.getName(), null);
    }
    @Test
    void attackHero(){
        Hero h = new Hero(1,"Test");
        int currHealth = h.getCurrentHealth();
        mons.attack(h);
        assertTrue(h.getCurrentHealth() == currHealth - mons.attackPoints);

    }

    @Test
    void multipleAttack(){
        Hero h = new Hero(1,"Test");
        int currHealth = h.getCurrentHealth();
        mons.attack(h);
        assertTrue(h.getCurrentHealth() == currHealth - mons.attackPoints);
        currHealth = h.getCurrentHealth();
        mons.attack(h);
        assertTrue(h.getCurrentHealth() == currHealth - mons.attackPoints);

    }

    @Test
    void isDeadTest(){
        mons.getHit(mons.health);
        assertTrue(mons.getIsDead());
    }

    @Test
    void isNotDeadTest(){
        mons.getHit(mons.health -1);
        assertFalse(mons.getIsDead());
    }

    @Test

    void testGetHit() {
        assertFalse(mons.getIfInSight());
        mons.changeThisSight();
        assertTrue ( mons.getIfInSight());
        mons.getHit(0);
        assertEquals(mons.getHealth(), 2);
    }

    @Test
    void changeMultipleInSight() {
        assertFalse(mons.getIfInSight());
        mons.changeThisSight();
        assertTrue ( mons.getIfInSight());
        mons.changeThisSight();
        assertFalse(mons.getIfInSight());
    }

    @Test
    void testHitTillDead(){
        mons.getHit(2);
        assertEquals(mons.getHealth(), 0);
        assertTrue(mons.getIsDead());
    }

    @Test
    void updatePositionCanMove(){
        assertTrue(mons.getCanMove());
        mons.updatePosition(1,1);
        assertEquals(mons.getPosY(),1);
        assertEquals(mons.getPosX(),1);

    }
    @Test
    void updatePosCanNotMove() {
        mons.changeCanMove();
        assertFalse(mons.getCanMove());
        mons.updatePosition(1,1);
        assertEquals(mons.getPosY(),0);
        assertEquals(mons.getPosX(),0);
    }

    @Test
    void multipleChangeCanMove() {
        assertTrue(mons.getCanMove());
        mons.changeCanMove();
        assertFalse(mons.getCanMove());
        mons.changeCanMove();
        assertTrue(mons.getCanMove());
    }

    @Test
    void updatePosMoveTooMuch() {
        assertTrue(mons.getCanMove());
        mons.updatePosition(5,5);
        assertEquals(mons.getPosY(),1);
        assertEquals(mons.getPosX(),1);
    }


}
