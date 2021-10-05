package model;

import java.util.ArrayList;

public class Cards {
    protected String nameOfCard;
    protected String description;
    protected String type;
    protected int hitPoints;
    protected boolean canRegenerate;

    public Cards(String name, String des, String type, int hitPoints, boolean reg) {
        nameOfCard = name;
        description = des;
        this.type = type;
        this.hitPoints = hitPoints;
        canRegenerate = false;
    }

    //EFFECTS: return name of this card
    public String getNameOfCard() {
        return nameOfCard;
    }

    //EFFECTS: return description of this card
    public String getDescription() {
        return description;
    }

    //EFFECTS: return type of this card
    public String getType() {
        return type;
    }

    //MODIFIES: Hero
    //EFFECTS: perform behavior of card on hero
    public void performOnHero(Hero h) {
    }

    //MODIFIES: SmallMonsters
    //EFFECTS: perform behavior of card
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
