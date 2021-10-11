package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Tests for Rat class
public class RatTest {
    SmallMonsters rat;

    @BeforeEach
    void setUp () {
         rat = new Rat(0,0);
    }

    @Test
    void checkChangeThisSight(){
        rat.changeThisSight();
        assertTrue(rat.getIfInSight());
    }
    @Test
    void getName() {
        assertEquals(rat.getName(), "Rat");
    }

}
