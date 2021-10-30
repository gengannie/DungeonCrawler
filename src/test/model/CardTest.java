package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// Tests for Cards class and some StunCard (subclass) behaviors
public class CardTest {
    Cards c;

    @BeforeEach
    void setUp(){
        c = new Cards("test", "this is a test card", 5, true);
    }

    @Test
    void getName(){
        assertEquals(c.getNameOfCard(), "test");
    }

    @Test
    void getDes(){
        assertEquals(c.getDescription(), "this is a test card");
    }

    @Test
    void performOnHero() {
        Hero aHero = new Hero(3,"A");
        int beforeHealth = aHero.getCurrentHealth();
        aHero.getAttacked(c.hitPoints);
        c.performOnHero(aHero);
        assertEquals(aHero.getCurrentHealth(), beforeHealth);

    }
    @Test
    void performOnMonsters() {
        SmallMonsters newMons = new Rat(0,0);
        SmallMonsters newMons2 = new Rat(0,0);
        ArrayList<SmallMonsters> listOfSmallM = new ArrayList<>();
        newMons.changeThisSight();
        listOfSmallM.add(newMons);
        listOfSmallM.add(newMons2);
        Hero aHero = new Hero(3,"A");
        c.performOnMonsters(aHero, listOfSmallM);
        assertEquals(newMons.getHealth(), 2 - 5);
        assertFalse(newMons.getCanMove());
    }
    @Test
    void performOnMonstersLoopThroughForLoop() {
        SmallMonsters newMons = new Rat(0,0);
        SmallMonsters newMons2 = new Rat(0,0);
        ArrayList<SmallMonsters> listOfSmallM = new ArrayList<>();
        newMons2.changeThisSight();
        listOfSmallM.add(newMons);
        listOfSmallM.add(newMons2);
        Hero aHero = new Hero(3,"A");
        c.performOnMonsters(aHero, listOfSmallM);
        assertEquals(newMons.getHealth(), 2);
        assertTrue(newMons.getCanMove());
        assertEquals(newMons2.getHealth(), 2 - 5);
        assertFalse(newMons2.getCanMove());
    }
    @Test
    void performOnNoMonstersLoopThroughForLoop() {
        SmallMonsters newMons = new Rat(0,0);
        SmallMonsters newMons2 = new Rat(0,0);
        ArrayList<SmallMonsters> listOfSmallM = new ArrayList<>();
        listOfSmallM.add(newMons);
        listOfSmallM.add(newMons2);
        Hero aHero = new Hero(3,"A");
        c.performOnMonsters(aHero, listOfSmallM);
        assertEquals(newMons.getHealth(), 2);
        assertTrue(newMons.getCanMove());
        assertEquals(newMons2.getHealth(), 2 );
        assertTrue(newMons2.getCanMove());
    }

}
