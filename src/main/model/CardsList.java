package model;

import java.util.ArrayList;

// Represents the list of cards the Hero can interact with
public class CardsList {
    private ArrayList<Cards> listOfCard;

    //EFFECTS: sets listOfCards with default cards
    public CardsList() {
        listOfCard = new ArrayList<>();
        listOfCard.add(new HealingPotion());
        listOfCard.add(new StunCard());

    }

    public void resetInventory() {
        listOfCard = new ArrayList<>();
    }

    //EFFECTS: print individual card name and descriptions
    public ArrayList<String> getCardsDescription() {
        ArrayList<String> listOfNamesAndDes = new ArrayList<>();
        for (Cards c : listOfCard) {
            listOfNamesAndDes.add(c.getNameOfCard());
            listOfNamesAndDes.add(c.getDescription());
        }
        return listOfNamesAndDes;
    }

    //REQUIRES: this ind is valid (exists in arraylist)
    //EFFECTS: return card in listOfCard
    public Cards getCardByIndex(int ind) {
        return listOfCard.get(ind);
    }

    //MODIFIES: this
    //EFFECTS: updates listOfCard so that it contains a new card
    public void getNewStunCard() {
        Cards newCard = new StunCard();
        listOfCard.add(newCard);
    }

    //MODIFIES: this
    //EFFECTS: updates listOfCard so that it contains a new card
    public void getNewHealingPot() {
        Cards newCard = new HealingPotion();
        listOfCard.add(newCard);
    }

    //REQUIRES: ind to be a valid index
    //MODIFIES: this
    //EFFECTS: removes card from inventory specified by valid index
    public void removeCard(int ind) {
        listOfCard.remove(ind);
    }

    //EFFECTS: returns how many cards are in card inventory
    public int getSize() {
        return listOfCard.size();
    }
}
