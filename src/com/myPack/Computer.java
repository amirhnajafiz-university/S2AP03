package com.myPack;

import java.util.ArrayList;

/**
 * This class is a subclass of the player class
 * and it is for the computer play to play against you.
 *
 */
public class Computer extends Player {

    // This is for getting the middle card for computer
    private Card middleCard;

    // This is for getting the middle wild card color
    private int middleColor;

    /**
     * The main constructor of the class.
     *
     * @param playerNumber the number of player
     */
    public Computer(int playerNumber) {
        super(playerNumber, 1);
    }

    /**
     * This method will give the computer the middle card
     * and the color of the middle card so the computer
     * can make a choice.
     *
     * @param middleColor the card color in the middle
     * @param middleCard the card in the middle
     * @return the card that the computer chose
     */
    public Card passTheData(int middleColor, Card middleCard){
        this.middleColor = middleColor;
        this.middleCard = middleCard;
        return choose();
    }

    /**
     * This method will chose a card for computer and will
     * put it in the middle.
     *
     * @return the card that the computer chose
     */
    private Card choose(){

        ArrayList<Card> hand = getCards();

        if(middleCard instanceof WildCard){

            WildCard newMiddle = (WildCard) middleCard;

            if(newMiddle.getTypeOfWild().equals("wildDraw") && checkForDraw() && !checkForColor(middleColor, -2, null)){

                for(int i = 0; i < hand.size(); i++){

                    if(hand.get(i).getType().equals("wild")){

                        WildCard temp = (WildCard) hand.get(i);

                        if(temp.getTypeOfWild().equals("wildDraw"))
                            return giveACard(i);
                    }
                }
            }
        }

        if(middleCard instanceof BonusCard){

            BonusCard newMiddle = (BonusCard) middleCard;

            if(newMiddle.getBonusType().equals("draw") && checkForBonusDraw()){

                for(int i = 0; i < hand.size(); i++){

                    if(hand.get(i).getType().equals("bonus")){

                        BonusCard temp = (BonusCard) hand.get(i);
                        if(temp.getBonusType().equals("draw"))
                            return giveACard(i);
                    }
                }
            }
        }

        for(int i = 0; i < hand.size(); i++){

            if(checkWithMiddle(hand.get(i))) {
                return giveACard(i);
            }

        }
        return null;
    }

    /**
     * This method will help the computer to see
     * if the card he chose is valid or not.
     *
     * @param temp the chosen card
     * @return true or false
     */
    private boolean checkWithMiddle(Card temp){

        if(temp instanceof WildCard){

            if(middleCard instanceof WildCard){

                if(!checkForColor(middleColor, -2, null))
                    return true;
                else
                    return false;
            } else {

                if(middleCard instanceof BonusCard){

                    if(!checkForColor(middleCard.getColor(), -1, ((BonusCard) middleCard).getBonusType())){
                        return true;
                    } else {
                        return false;
                    }
                } else {

                    NormalCard newMiddle = (NormalCard) middleCard;

                    if (!checkForColor(middleCard.getColor(), newMiddle.getCardNumber(), null))
                        return true;
                    else
                        return false;
                }
            }
        } else if(temp instanceof BonusCard){

            BonusCard newTemp = (BonusCard) temp;

            if(middleCard instanceof WildCard){
                return newTemp.getColor() == middleColor;
            } else if(middleCard instanceof BonusCard){

                BonusCard newMiddle = (BonusCard) middleCard;
                return newMiddle.getColor() == newTemp.getColor() || newMiddle.getBonusType().equals(newTemp.getBonusType());
            } else {

                NormalCard newMiddle = (NormalCard) middleCard;
                return newMiddle.getColor() == newTemp.getColor();
            }
        } else {

            NormalCard newTemp = (NormalCard) temp;
            if(middleCard instanceof WildCard){
                return newTemp.getColor() == middleColor;
            } else if(middleCard instanceof BonusCard){

                BonusCard newMiddle = (BonusCard) middleCard;
                return newMiddle.getColor() == newTemp.getColor();
            } else {

                NormalCard newMiddle = (NormalCard) middleCard;
                return newMiddle.getColor() == newTemp.getColor() || newMiddle.getCardNumber() == newTemp.getCardNumber();
            }
        }
    }
}
