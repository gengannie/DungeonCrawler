package model;

// A pile of coins that is a subclass of GenericObject
public class CoinPile extends GenericObject {
    private int coinAmount;

    public CoinPile(boolean inWorld, int x, int y, int amount) {
        super(inWorld, true, x, y);
        coinAmount = amount;
    }

    //MODIFIES: Hero
    //EFFECTS: adds certain amount of coins to Hero
    @Override
    public void performBehavior(Hero h) {
        h.addCoins(coinAmount);
    }

}
