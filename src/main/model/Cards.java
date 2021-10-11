package model;

import java.util.ArrayList;

// Superclass of different types of cards, contains common behavior and characteristics of cards
public class Cards {
    protected String nameOfCard;
    protected String description;
    protected int hitPoints;
    protected boolean canRegenerate;

    public Cards(String name, String des, int hitPoints, boolean reg) {
        nameOfCard = name;
        description = des;
        this.hitPoints = hitPoints;
        canRegenerate = reg;
    }

    //EFFECTS: return name of this card
    public String getNameOfCard() {
        return nameOfCard;
    }

    //EFFECTS: return description of this card
    public String getDescription() {
        return description;
    }

    //MODIFIES: Hero
    //EFFECTS: perform behavior of card
    public void performOnHero(Hero h) {
        h.heal(hitPoints);
    }

    //MODIFIES: SmallMonsters
    //EFFECTS: perform behavior of card on list of monsters given
    public void performOnMonsters(Hero h, ArrayList<SmallMonsters> listOfSmallM) {
        for (SmallMonsters m : listOfSmallM) {
            if (m.getIfInSight()) {
                m.changeCanMove();
                m.getHit(hitPoints);
                break;
            }
        }
    }
}
