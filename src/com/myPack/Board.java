package com.myPack;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * This is the main part of the program to control each player
 * and the game and ....
 *
 */
public class Board {

    // The list of players
    private ArrayList<Player> players;

    // The list of cards
    private ArrayList<Card> cards;

    // To see how many wildDraws are in the middle right now (0 == not)
    private int isWildDraw;

    // To see if there is wild change color is in middle (0 == not)
    private int colorWild;

    // To see if we have a draw card in the middle (0 == not) and to see the color of the middle
    private int isDraw;

    // To see what is in the middle right now
    private Card middleCard;

    // This is to see hows turn is it
    private int turn;

    // This is to check the rotation in the game
    private boolean isClockwise;

    // This is for Print Class
    private int graphicType;

    // This is for checking if the game is just started
    private boolean isFirstRound;

    /**
     * Main constructor of the program.
     *
     */
    public Board(int numberOfPlayers, int gameMode, int graphicType){
        players = new ArrayList<>();
        cards = new ArrayList<>();
        middleCard = null;
        isWildDraw = 0;
        colorWild = 0;
        this.graphicType = graphicType;
        Random r = new Random();
        this.turn = r.nextInt(numberOfPlayers);
        this.isClockwise = true;
        this.isFirstRound = true;

        // To create the game
        createCards();
        createPlayer(gameMode, numberOfPlayers);
        setTheMiddleCard();
    }

    /**
     * This method is to change the status of
     * the round to say we are not in the first
     * round of the game.
     *
     */
    public void setFirstRound() {
        isFirstRound = false;
    }

    /**
     * This is for getting the turn of the players.
     *
     * @return the current turn
     */
    public int getTurn() {
        return turn;
    }

    /**
     * To see if we are going clockwise or
     * counter clockwise.
     *
     * @return true for clockwise and false for counter clockwise
     */
    public boolean isClockwise() {
        return isClockwise;
    }

    /**
     * This method is for creating 108 cards in random places.
     *
     */
    private void createCards(){
        ArrayList<Card> tempList = new ArrayList<>();
        for(int i = 1; i < 5; i++){
            for(int j = 0; j < 10; j++){
                if(j == 0) {
                    NormalCard tempCard = new NormalCard(i, j);
                    tempList.add(tempCard);
                } else {
                    NormalCard temp1 = new NormalCard(i, j);
                    NormalCard temp2 = new NormalCard(i, j);
                    tempList.add(temp1);
                    tempList.add(temp2);
                }
            }
            BonusCard tempBonus1 = new BonusCard(i, "reverse");
            BonusCard tempBonus2 = new BonusCard(i, "reverse");
            BonusCard tempBonus3 = new BonusCard(i, "skip");
            BonusCard tempBonus4 = new BonusCard(i, "skip");
            BonusCard tempBonus5 = new BonusCard(i, "draw");
            BonusCard tempBonus6 = new BonusCard(i, "draw");
            tempList.add(tempBonus1);
            tempList.add(tempBonus2);
            tempList.add(tempBonus3);
            tempList.add(tempBonus4);
            tempList.add(tempBonus5);
            tempList.add(tempBonus6);
        }
        for(int i = 0; i < 4; i++){
            WildCard tempWild1 = new WildCard(0, "wildDraw");
            WildCard tempWild2 = new WildCard(0, "changeColor");
            tempList.add(tempWild1);
            tempList.add(tempWild2);
        }

        Random random = new Random();
        while(true){
            if(tempList.size() == 0)
                break;
            int index = random.nextInt(tempList.size());
            cards.add(tempList.get(index));
            tempList.remove(index);
        }
    }

    /**
     * This method is for creating the players
     * based on what mode and the number of players.
     * And we give each of them 7 cards.
     *
     * @param gameMode single mode or multi player
     * @param numberOfPlayers the number of the game players
     */
    private void createPlayer(int gameMode, int numberOfPlayers){
        if(gameMode == 1){
            Player temp = new Player(0, 0);
            players.add(temp);
            for(int i = 1; i < numberOfPlayers; i++){
                Computer tempCom = new Computer(i);
                players.add(tempCom);
            }
        } else if(gameMode == 2){
            for(int i = 0; i < numberOfPlayers; i++){
                Player tempCom = new Player(i, 0);
                players.add(tempCom);
            }
        }
        for(int i = 0; i < players.size(); i++){
            for(int j = 0; j < 7; j++){
                giveCards(i);
            }
        }
        setTheMiddleCard();
    }

    /**
     * This is to set the middle card at the
     * start of the game.
     * Middle card is a normal card or a bonus card.
     *
     */
    private void setTheMiddleCard(){
        int i = 0;
        while (true) {
            if(! (cards.get(i) instanceof WildCard)){
                middleCard = cards.get(i);
                cards.remove(i);
                break;
            } else {
                i++;
            }
        }
    }

    /**
     * This method is for mixing the cards
     * each time.
     *
     */
    public void shuffleCards(){
        ArrayList<Card> templist = new ArrayList<>();
        Random random = new Random();
        while(true){
            if(cards.size() == 0)
                break;
            int index = random.nextInt(cards.size());
            templist.add(cards.get(index));
            cards.remove(index);
        }
        cards = templist;
    }

    /**
     * This method is for give cards to a player.
     *
     * @param index the number of player
     */
    public void giveCards(int index){
        players.get(index).addCard(cards.get(0));
        cards.remove(0);
    }

    /**
     * This method will check the turn and
     * will change the turn.
     *
     */
    public void checkTurn(){
        if(isClockwise) {
            turn++;
            if(turn > players.size() - 1){
                turn = 0;
            }
        } else {
            turn--;
            if(turn < 0){
                turn = players.size() - 1;
            }
        }
    }

    /**
     * This method is for getting the number of
     * cards of the player that is playing.
     *
     * @return the number of cards
     */
    public int getPlayerNumberOfCards(){
        if(players.get(turn).getPlayerType() == 0){
            return players.get(turn).getNumberOfCard();
        }
        return 0;
    }

    /**
     * This method is for a player to chose a card
     * and put it in the middle if it was valid.
     *
     * @param index the place of the card inside the hand
     */
    public boolean playTheTurn(int index){
        Card temp = players.get(turn).giveACard(index);

        if(checkCard(temp)){

            cards.add(middleCard);
            middleCard = temp;
            players.get(turn).updateChange();
            checkMiddle();

            return true;
        } else {
            return false;
        }
    }

    /**
     * This is an override method for the computer play.
     *
     * @param temp the card we chose
     */
    public void playTheTurn(Card temp){

        cards.add(middleCard);
        middleCard = temp;
        players.get(turn).updateChange();
        checkMiddle();
    }

    /**
     * This method will check what did the player put
     * in the middle.
     * This is for bonus cards and wild cards to check their
     * type and import their job.
     *
     */
    public void checkMiddle(){

        if(middleCard instanceof WildCard){

            WildCard newMiddleCard = (WildCard) middleCard;

            if(newMiddleCard.getTypeOfWild().equals("wildDraw")){

                isWildDraw++;

                if(isComputerTurn()){
                    Random random = new Random();
                    colorWild = random.nextInt(4) + 1;
                } else {

                    Scanner scanner = new Scanner(System.in);
                    while (true) {
                        System.out.print("\nEnter Your Color ( 1 for Red / 2 for Blue / 3 for Green / 4 for Yellow ) > ");
                        colorWild = scanner.nextInt();
                        if (colorWild < 5 && colorWild > 0)
                            break;
                    }

                }

                checkTurn();

                if(!players.get(turn).checkForDraw() || players.get(turn).checkForColor(colorWild, -2, null)){
                    System.out.println("\nSorry Player " + (turn + 1) + " , The Wild Draw Got You. You Get " + (4 * isWildDraw) + " Cards. And You Lose A Turn.\n");
                    for(int i = 0; i < isWildDraw; i++){
                        giveCards(turn);
                        giveCards(turn);
                        giveCards(turn);
                        giveCards(turn);
                    }
                    isWildDraw = 0;
                    checkTurn();
                }

            } else {

                if(isComputerTurn()){
                    Random random = new Random();
                    colorWild = random.nextInt(4) + 1;
                } else {
                    Scanner scanner = new Scanner(System.in);
                    while (true) {
                        System.out.print("\nEnter Your Color ( 1 for Red / 2 for Blue / 3 for Green / 4 for Yellow ) > ");
                        colorWild = scanner.nextInt();
                        if (colorWild < 5 && colorWild > 0)
                            break;
                    }
                }

                checkTurn();
            }
        } else if(middleCard instanceof BonusCard){

            BonusCard newMiddleCard = (BonusCard) middleCard;

            if(newMiddleCard.getBonusType().equals("reverse")){

                isClockwise = !isClockwise;

                if(players.size() != 2)
                    checkTurn();

            } else if(newMiddleCard.getBonusType().equals("skip")){

                checkTurn();

                System.out.println("\nSorry Player " + (turn + 1) + " You Got Skipped.\n");

                checkTurn();
            } else {

                isDraw++;

                checkTurn();

                if(!players.get(turn).checkForBonusDraw() || isFirstRound){
                    System.out.println("\nSorry Player " + (turn + 1) + " , You Got Draw. You Get " + (2 * isDraw) + " Cards. And You Lose A Turn.\n");
                    for(int i = 0; i < isDraw; i++){
                        giveCards(turn);
                        giveCards(turn);
                    }
                    isDraw = 0;
                    checkTurn();
                }

            }
        } else {

            isDraw = 0;
            isWildDraw = 0;
            colorWild = 0;

            checkTurn();
        }
    }

    /**
     * This will check the input card with the middle card
     * to see if it is able to put this card on the middle card.
     *
     * @param temp the chosen card
     * @return true or false
     */
    private boolean checkCard(Card temp){

        if(temp instanceof WildCard){

            if(middleCard instanceof WildCard){

                if(!players.get(turn).checkForColor(colorWild, -2, null))
                    return true;
                else
                    return false;
            } else {

                if(middleCard instanceof BonusCard){

                    if(!players.get(turn).checkForColor(middleCard.getColor(), -1, ((BonusCard) middleCard).getBonusType())){
                        return true;
                    } else {
                        return false;
                    }
                } else {

                    NormalCard newMiddle = (NormalCard) middleCard;

                    if (!players.get(turn).checkForColor(middleCard.getColor(), newMiddle.getCardNumber(), null))
                        return true;
                    else
                        return false;
                }
            }
        } else if(temp instanceof BonusCard){

            BonusCard newTemp = (BonusCard) temp;

            if(middleCard instanceof WildCard){
                return newTemp.getColor() == colorWild;
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
                return newTemp.getColor() == colorWild;
            } else if(middleCard instanceof BonusCard){

                BonusCard newMiddle = (BonusCard) middleCard;
                return newMiddle.getColor() == newTemp.getColor();
            } else {

                NormalCard newMiddle = (NormalCard) middleCard;
                return newMiddle.getColor() == newTemp.getColor() || newMiddle.getCardNumber() == newTemp.getCardNumber();
            }
        }
    }

    /**
     * This method is to check if a player is able to put a card
     * in the middle or not.
     *
     * @return true or false
     */
    public boolean handCheck(){

        ArrayList<Card> playerCards = players.get(turn).getCards();
        for(Card i : playerCards){
            if(checkCard(i))
                return true;
        }
        return false;
    }

    /**
     * This is to see if it is the computer turn or the
     * player turn.
     *
     * @return true or false
     */
    public boolean isComputerTurn(){
        return players.get(turn).getPlayerType() == 1;
    }

    /**
     * This method will let the computer plays if it was
     * the computer turn to play.
     *
     */
    public void computerPlay(){
        Computer temp = (Computer) players.get(turn);
        playTheTurn(temp.passTheData(colorWild, middleCard));
    }

    /**
     * This is for the user to see its hand.
     *
     */
    public void printTheHand(){
        if(players.get(turn).getPlayerType() == 0){
            System.out.println("Player " + (turn + 1) + " here is your cards : ");
            PrintTheBoard show = new PrintTheBoard(players.get(turn).getCards(), graphicType);
        }
    }

    /**
     * This method is for showing the middle card.
     *
     */
    public void printTheMiddle(){

        PrintTheBoard show = new PrintTheBoard(middleCard, graphicType);

        if(middleCard instanceof WildCard){

            String color = null;
            switch (colorWild) {
                case 1:
                    color = "RED";
                    break;
                case 2:
                    color = "BLUE";
                    break;
                case 3:
                    color = "GREEN";
                    break;
                case 4:
                    color = "YELLOW";
                    break;
            }
            System.out.println("The Ground Color Is : " + color);
        }
    }

    /**
     * This is to check if the game is over or not.
     *
     * @return true or false
     */
    public boolean checkGame(){
        for(Player i : players){
            if(i.getNumberOfCard() == 0)
                return true;
        }
        return false;
    }

    /**
     * This is a getter method for getting the players
     * and printing the result.
     *
     * @return the list of the players
     */
    public ArrayList<Player> getPlayers(){
        return players;
    }
}
