package com.myPack;

/**
 * This class is a subclass of the card class
 * and it has only the number of card as an extra field.
 *
 */
public class NormalCard extends Card {

    // This is the number of the card
    private int cardNumber;

    /**
     * The main constructor of the class for setting the
     * color and type and value.
     *
     * @param color it can be 0 to 4
     * @param cardNumber is the number of the card from 0 to 9
     */
    public NormalCard(int color, int cardNumber) {
        super(color, "normal", cardNumber);
        this.cardNumber = cardNumber;
    }

    /**
     * A getter method for getting the number of the card.
     *
     * @return the number of the card
     */
    public int getCardNumber() {
        return cardNumber;
    }
}
