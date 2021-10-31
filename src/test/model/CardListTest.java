package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardListTest {
    CardsList cl;

    @BeforeEach
    void setUp() {
        cl = new CardsList();
    }

    @Test
    void getDesTest() {
        ArrayList<String> expectedAns = cl.getCardsDescription();
        int thisInd = 0;
        for (int ind = 0; ind < expectedAns.size(); ind++) {
            if ((ind + 1) % 2 == 0) {
                assertEquals(expectedAns.get(ind), cl.getCardByIndex(thisInd).getDescription());
                thisInd += 1;

            } else {
                assertEquals(expectedAns.get(ind), cl.getCardByIndex(thisInd).getNameOfCard());
            }
        }
    }

}
