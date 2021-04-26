package com.myPack;

/**
 * Bonus card are a kind of card that do a special
 * thing base of their type.
 * This class is a subclass of the Card class.
 *
 */
public class BonusCard extends Card {

    // This is the type of this kind of card
    private String bonusType;

    /**
     * The main constructor of the class for setting the
     * color and type and value.
     *
     * @param color it can be 0 to 4
     * @param bonusType it is the type of the bonus
     */
    public BonusCard(int color, String bonusType) {
        super(color, "bonus", 20);
        this.bonusType = bonusType;
    }

    /**
     * A getter method for getting the type of the bonus
     * of this card.
     *
     * @return the type of bonus
     */
    public String getBonusType() {
        return bonusType;
    }
}
