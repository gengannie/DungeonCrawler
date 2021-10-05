package model;

public class CoinPile extends GenericObject {
    private int coinAmount;

    public CoinPile(boolean inWorld, int x, int y, int amount) {
        super(inWorld, true, x, y);
        coinAmount = amount;
    }

    @Override
    public void getBehavior(Hero h) {
        h.addCoins(coinAmount);
    }

}
