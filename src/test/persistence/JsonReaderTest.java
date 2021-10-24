package persistence;

import model.Hero;
import model.Rat;
import model.SmallMonsters;
import org.junit.jupiter.api.Test;
import ui.GameWorld;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// This class references code from this CPSC210/JsonSerializationDemo
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// tests for JsonWriter class in persistence package
public class JsonReaderTest extends JsonTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            GameWorld testGame = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // expected
        }
    }

    // This method references code from this website
    // Link: https://stackoverflow.com/questions/15699953/how-do-i-parse-json-into-an-int
    @Test
    void testReadingWorldGrid() {
        JsonReader reader = new JsonReader("./data/testReadingWorldGrid.json");
        try {
            GameWorld testGame = reader.read();
            int[][] idealWorldGrid = new int[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (j != 2) {
                        idealWorldGrid[i][j] = 1;
                    } else {
                        idealWorldGrid[i][j] = 0;
                    }
                }
            }
            int[][] newWorldGrid = testGame.returnWorldGrid();
            checkEqualWorldGrid(idealWorldGrid, newWorldGrid);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReadingMonsters() {
        JsonReader reader = new JsonReader("./data/testReadingWorldGrid.json");
        try {
            GameWorld testGame = reader.read();
            ArrayList<SmallMonsters> idealMonsters = new ArrayList<>();
            SmallMonsters rat = new Rat(1, 10);
            rat.changeCanMove();
            idealMonsters.add(rat);

            rat = new Rat(5, 2);
            rat.changeThisSight();
            idealMonsters.add(rat);

            rat = new Rat(10, 1);
            rat.setIsDead(true);
            rat.getHit(2);
            idealMonsters.add(rat);

            ArrayList<SmallMonsters> testMonsters = testGame.getMonsters();
            assertEquals(testMonsters.size(), idealMonsters.size());
            for (int i = 0; i < 3; i++) {
                SmallMonsters testRat = testMonsters.get(i);
                rat = idealMonsters.get(i);
                assertFalse(testRat == null);
                checkEqualsRats(rat, testRat);
            }
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReadingHero() {
        JsonReader reader = new JsonReader("./data/testReadingWorldGrid.json");
        try {
            GameWorld testGame = reader.read();
            Hero idealHero = new Hero(1, "a");
            idealHero.moveHero(10,11);
            idealHero.setHealthAndMana(10, 3);
            Hero testHero = testGame.getHero();
            assertFalse(testHero == null);
            checkEqualsHero(testHero, idealHero);

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


}

