package persistence;

import model.Hero;
import model.SmallMonsters;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Includes comparison methods to check if read/write items in json are the same as expected items
public class JsonTest {

    //EFFECTS: checks whether or not the attributes of two rats are equal
    protected void checkEqualsRats(SmallMonsters rat, SmallMonsters testRat) {
        assertEquals(testRat.getPosX(), rat.getPosX());
        assertEquals(testRat.getPosY(), rat.getPosY());
        assertEquals(testRat.getIfInSight(), rat.getIfInSight());
        assertEquals(testRat.getHealth(), rat.getHealth());
        assertEquals(testRat.getCanMove(), rat.getCanMove());
        assertEquals(testRat.getIsDead(), rat.getIsDead());
    }

    //EFFECTS: checks whether or not the attributes of two heros are equal
    protected void checkEqualsHero(Hero idealHero, Hero testHero) {
        assertEquals(idealHero.getPosX(), testHero.getPosX());
        assertEquals(idealHero.getPosY(), testHero.getPosY());
        assertEquals(idealHero.getCurrentHealth(), testHero.getCurrentHealth());
        assertEquals(idealHero.getName(), testHero.getName());
        assertEquals(idealHero.getCardDes(), testHero.getCardDes());
        assertEquals(idealHero.getIsDead(), testHero.getIsDead());
    }


    //EFFECTS: checks whether or not two world grids are equal
    protected void checkEqualWorldGrid(int [][] idealWorldGrid, int [][] newWorldGrid) {
        assertEquals(idealWorldGrid.length, newWorldGrid.length);
        int len = idealWorldGrid.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                assertEquals(idealWorldGrid[i][j], newWorldGrid[i][j]);
            }
        }
    }

    //EFFECTS: checks whether or not list of two monsters are equal
    protected void checkEqualsListOfMonsters(ArrayList<SmallMonsters> idealList, ArrayList<SmallMonsters> testList) {
        assertEquals(idealList.size(), testList.size());
        int len = testList.size();
        for (int i = 0; i < len; i++) {
            checkEqualsRats(idealList.get(i), testList.get(i));
        }
    }
}
