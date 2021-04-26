package com.myPack;

/**
 * Wild cards can be use everywhere every time and
 * they do things base on their type.
 * This class is a subclass of the Card class.
 *
 */
public class WildCard extends Card{

    // This is for the type of the wild
    private String typeOfWild;

    /**
     * The main constructor of the class for setting the
     * color and type and value.
     *
     * @param color it can be 0 to 4
     * @param typeOfWild it is the type of the wild card
     */
    public WildCard(int color, String typeOfWild) {
        super(color, "wild", 50);
        this.typeOfWild = typeOfWild;
    }

    /**
     * A getter method for getting the type of the
     * wild card.
     *
     * @return the type of the wild card
     */
    public String getTypeOfWild() {
        return typeOfWild;
    }
}
