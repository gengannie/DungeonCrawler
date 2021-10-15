package model;

import java.util.ArrayList;
import java.util.Random;

// Represents the list of cards the Hero can interact with
public class CardsList {
    private ArrayList<Cards> listOfCard;

    //EFFECTS: sets listOfCards with default cards
    public CardsList() {
        listOfCard = new ArrayList<>();
        listOfCard.add(new HealingPotion());
        listOfCard.add(new StunCard());

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
    //EFFECTS: updates listOfCard so that it contains a new card. TODO: in the future make this process random
    public void getNewRandomCard() {
        Cards newCard = new StunCard();
        listOfCard.add(newCard);

    }
}
