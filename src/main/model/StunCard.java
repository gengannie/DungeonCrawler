package model;

public class StunCard extends Cards {

    public StunCard() {
        super("Stun", "stuns your opponent for one turn", "attack", 1, true);
    }

    //MODIFIES: Hero
    //EFFECTS: perform behavior of card
    public void performBehavior(Monsters m) {
        m.changeCanMove();
    }
}
