package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

// Tests for CoinPile class and its inherited methods
public class CoinPileTest {
    GenericObject coinPile;

    @BeforeEach
    void setUp() {
        coinPile = new CoinPile(true, 0, 0, 5);
    }

    @Test
    void behavior () {
        Hero aHero = new Hero(2,"a");
        coinPile.performBehavior(aHero);
        assertEquals(aHero.getCoinsInInventory(), 5);
    }

    @Test
    void isInWorld() {
        assertTrue(coinPile.getPresence());
    }

    @Test
    void changeInWorld() {
        assertTrue(coinPile.getPresence());
        coinPile.changePresence();
        assertFalse(coinPile.getPresence());
    }

    @Test
    void changeMultPresence() {
        assertTrue(coinPile.getPresence());
        coinPile.changePresence();
        assertFalse(coinPile.getPresence());
        coinPile.changePresence();
        assertTrue(coinPile.getPresence());
    }

    @Test
    void canBeTouched() {
        assertTrue(coinPile.getCanTouch());
    }

    @Test
    void getXYPos() {
        assertEquals(coinPile.getPosX(), 0);
        assertEquals(coinPile.getPosY(), 0);
    }

}
