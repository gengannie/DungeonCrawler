package persistence;

import model.Hero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.GameWorld;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// This class references code from this CPSC210/JsonSerializationDemo
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.
    private GameWorld gameWorld;

    @BeforeEach
    void setUp() {
        gameWorld = new GameWorld(0);
    }

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }
    @Test
    void testWriterEmptyWorldGrid() {
        try {
            Hero testHero = new Hero(1,"test");
            gameWorld.addHeroToGame(testHero);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorldGrid.json");
            writer.open();
            writer.write(gameWorld);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorldGrid.json");
            GameWorld loadedGame = reader.read();
            checkEqualsHero(loadedGame.getHero(), testHero);
            assertEquals(loadedGame.getMonsters().size(), 0);
            assertEquals(loadedGame.getMonsters(), gameWorld.getMonsters());
            checkEqualWorldGrid(loadedGame.returnWorldGrid(), gameWorld.returnWorldGrid());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}