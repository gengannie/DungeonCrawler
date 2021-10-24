package persistence;

import model.Hero;
import model.SmallMonsters;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkEqualsRats(SmallMonsters rat, SmallMonsters testRat) {
        assertEquals(testRat.getPosX(), rat.getPosX());
        assertEquals(testRat.getPosY(), rat.getPosY());
        assertEquals(testRat.getIfInSight(), rat.getIfInSight());
        assertEquals(testRat.getHealth(), rat.getHealth());
        assertEquals(testRat.getCanMove(), rat.getCanMove());
        assertEquals(testRat.getIsDead(), rat.getIsDead());
    }

    protected void checkEqualsHero(Hero idealHero, Hero testHero) {
        assertEquals(idealHero.getPosX(), testHero.getPosX());
        assertEquals(idealHero.getPosY(), testHero.getPosY());
        assertEquals(idealHero.getCurrentHealth(), testHero.getCurrentHealth());
        assertEquals(idealHero.getName(), testHero.getName());
        // TODO: assertEquals(idealHero.getCardDes(), testHero.getCardDes());
        assertEquals(idealHero.getIsDead(), testHero.getIsDead());
    }

    protected void checkEqualWorldGrid(int [][] idealWorldGrid, int [][] newWorldGrid) {
        assertEquals(idealWorldGrid.length, newWorldGrid.length);
        int len = idealWorldGrid.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                assertEquals(idealWorldGrid[i][j], newWorldGrid[i][j]);
            }
        }
    }
}
