package com.myPack;

/**
 * This class is the main superclass of all card
 * types.
 * This class holds the color of the card and the type
 * and the value of the card.
 *
 */
public class Card {

    // The color of the card
    protected int color;

    // The type of the card
    protected String type;

    // The value of the card
    protected int value;

    /**
     * The main constructor of the class for setting the
     * color and type and value.
     *
     * @param color it can be 0 to 4
     * @param type it can be normal or wild or bonus
     * @param value it is different base on the type
     */
    public Card(int color, String type, int value){
        this.color = color;
        this.type = type;
        this.value = value;
    }

    /**
     * A type getter method for getting the type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * A color getter method for getting the color.
     *
     * @return the color
     */
    public int getColor() {
        return color;
    }

    /**
     * A value getter method for getting the value.
     *
     * @return the value
     */
    public int getValue() {
        return value;
    }
}
