package model;

public class HealingPotion extends Cards {
    //EFFECTS: sets this card to certain functionalities
    public HealingPotion() {
        super("Healing Potion", "heals your hero by 7 points", 7, false);
    }

    @Override
    //MODIFIES: Hero
    //EFFECTS: perform behavior of card
    public void performOnHero(Hero h) {
        h.heal(hitPoints);
    }

}
