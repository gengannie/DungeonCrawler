package model;

import java.util.ArrayList;

// Card behavior of a card that freezes/ stuns monsters
public class StunCard extends Cards {
    //EFFECTS: initializes attributes of StunCard
    public StunCard() {
        super("Freeze", "Freezes a random opponent in sight", 1, true);
    }

}
