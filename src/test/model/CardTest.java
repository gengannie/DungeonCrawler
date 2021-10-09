package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

}
