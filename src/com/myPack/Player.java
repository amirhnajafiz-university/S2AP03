package com.myPack;

import java.util.ArrayList;

/**
 * This is the player class for keeping the each
 * player information like the cards and the type
 * of the player for single or multi player.
 *
 */
public class Player {

    // We need this for the last result
    private int playerNumber;

    // This is the array for keeping the cards of player
    private ArrayList<Card> cards;

    // This is for choosing a card
    private int chosenIndex;

    // This is for computer or human
    private int playerType;

    /**
     * The main constructor of the class.
     *
     * @param playerNumber the number of player
     * @param playerType the type of the player
     */
    public Player(int playerNumber, int playerType){
        this.playerNumber = playerNumber;
        cards = new ArrayList<>();
        this.playerType = playerType;
    }

    /**
     * This is for adding a card to the list of cards.
     *
     * @param temp the new card to be added
     */
    public void addCard(Card temp){
        cards.add(temp);
    }

    /**
     * This is for removing a card from the list.
     *
     * @param index the place of the card in hand
     */
    public void removeCard(int index){
        cards.remove(index);
    }

    /**
     * A getter method for getting the number of player.
     *
     * @return the number of player
     */
    public int getPlayerNumber() {
        return playerNumber;
    }

    /**
     * A getter method for getting the number of cards.
     *
     * @return the number of cards
     */
    public int getNumberOfCard(){
        return cards.size();
    }

    /**
     * This is for getting the cards list.
     * This method is for computer Class.
     *
     * @return the list of cards
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

    /**
     * A getter method for getting the type of
     * the player.
     * This method is for computer Class.
     *
     * @return the type of the player
     */
    public int getPlayerType() {
        return playerType;
    }

    /**
     * This method is for choosing a card
     * and give it to the board Class.
     *
     * @param index the place of the card in list
     * @return the card we chose
     */
    public Card giveACard(int index){
        chosenIndex = index;
        return cards.get(index);
    }

    /**
     * If the card we chose was ok this
     * method will get that card out form the list.
     *
     */
    public void updateChange(){
        removeCard(chosenIndex);
    }

    /**
     * This method is for checking if we have
     * any wild cards.
     * This is useful for the hints and checking.
     *
     * @return true or false
     */
    public boolean checkForColor(int color, int number, String type){

        for(Card i : cards){
            if(i.getColor() == color)
                return true;
            if(i instanceof NormalCard && (number != -1 && number != -2)){
                NormalCard temp = (NormalCard) i;
                if(temp.getCardNumber() == number)
                    return true;
            }
            if(number == -1 && i instanceof BonusCard){
                BonusCard temp = (BonusCard) i;
                if(temp.getBonusType().equals(type)){
                    return true;
                }
            }
        }

        return false;
    }


    /**
     * This method is to see if we have any wildDraw
     * cards or not.
     * This will work for the isWild status.
     *
     * @return true or false
     */
    public boolean checkForDraw(){

        for(Card i : cards){
            if(i instanceof WildCard){
                if(((WildCard) i).getTypeOfWild().equals("wildDraw"))
                    return true;
            }
        }
        return false;
    }

    /**
     * This is method is to see if we have
     * a bonus draw card or not.
     *
     * @return true or false
     */
    public boolean checkForBonusDraw(){

        for(Card i : cards){
            if(i instanceof BonusCard){
                if(((BonusCard) i).getBonusType().equals("draw"))
                    return true;
            }
        }
        return false;
    }

    /**
     * This is to calculate the score of the player
     * by adding the cards value.
     *
     * @return the score of this player
     */
    public int getScore(){

        int score = 0;
        for(Card i : cards)
            score += i.getValue();
        return score;
    }
}
