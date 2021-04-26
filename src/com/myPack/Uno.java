package com.myPack;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is the main class of the program because it
 * is a connection between the players and the cards
 * and the methods and it is for playing the game.
 *
 */
public class Uno {

    // The number of players
    private int numberOfPlayers;

    // The mode of the game
    private int gameMode;

    // The archive of the last rounds
    private ArrayList<Result> results;

    // This is for each round
    private Board board;

    //  This will make a difference in output
    private int graphicType;

    /**
     * The main constructor of the program.
     *
     * @param numberOfPlayers the number of players of round
     * @param gameMode the single mode or multi player mode
     */
    public Uno(int numberOfPlayers, int gameMode, int graphicType){
        this.numberOfPlayers = numberOfPlayers;
        this.gameMode = gameMode;
        this.graphicType = graphicType;
        results = new ArrayList<>();
    }

    /**
     * This is the main menu of the game.
     *
     */
    public void startTheGame(){

        Scanner scanner = new Scanner(System.in);
        int input;

        while(true){
            System.out.print("Enter 1 For A New Round Or 2 For Exit > ");
            input = scanner.nextInt();
            if(input == 1)
                playUno();
            else if(input == 2)
                break;
            else
                System.out.print("Invalid Input.");
        }

        printAllResult();
        System.out.print("Thanks For Playing.");
    }

    /**
     * The playing method of this class to play the game.
     *
     */
    private void playUno(){
        board = new Board(numberOfPlayers, gameMode, graphicType);

        // Making the scanner
        Scanner scanner = new Scanner(System.in);

        // Getting the input
        int input;

        // The main loop of the program
        // But before it we check the middle card first
        board.checkMiddle();

        // This is to say we are passed the first card
        board.setFirstRound();

        while(true){

            // Printing the status
            printPlayers();

            // This is for showing the middle card
            board.printTheMiddle();

            // Check if the game is finish or not
            if(board.checkGame()){
                System.out.print("\nThe Round Is Finished.\n");
                Result result = new Result(board.getPlayers());
                results.add(result);
                printThisRound();
                break;
            }

            // Show the rotation type
            if(board.isClockwise())
                System.out.println("\nThe Rotation Is Clockwise. ( ---> )");
            else
                System.out.println("The Rotation Is Counter Clockwise. ( <--- )");

            // Shuffle the cards
            board.shuffleCards();

            // Showing the turn of the player
            System.out.println("\nPlayer " + (board.getTurn() + 1) + " Turn. Make Your Move.");

            // If it was computer turn
            if(board.isComputerTurn()){

                // To check if the bot has a valid card
                if(!board.handCheck())
                    board.giveCards(board.getTurn());

                // To check if the bot still doesn't have a valid card
                if(!board.handCheck()) {
                    board.checkTurn();
                    continue;
                }

                // Else we let the computer play
                board.computerPlay();
                continue;
            }

            // Then we show the hand of the player.
            board.printTheHand();

            // First we check to see if this player has a valid card
            if(!board.handCheck()) {
                System.out.println("\nYou Don't Have A Valid Card. Pick Another One.");

                // We continue to keep getting to get the right input
                while(true){
                    System.out.print("\nPress 1 To Pick A Card > ");
                    input = scanner.nextInt();
                    if(input == 1){
                        board.giveCards(board.getTurn());
                        break;
                    }
                }

                board.printTheHand();

                // The proses of giving the card if we did't have any valid card
                if(!board.handCheck()) {

                    while (true) {
                        System.out.print("\nYou Don't Have A Valid Card. Press 1 To Pass The Turn > ");
                        input = scanner.nextInt();
                        if (input == 1)
                            break;
                    }

                    board.checkTurn();
                    continue;
                }
            }

            while(true) {

                System.out.print("\nChose The Card You Want To Give > ");
                input = scanner.nextInt() - 1;

                if(input > -1 && input < board.getPlayerNumberOfCards()){
                    if(board.playTheTurn(input)) {
                        break;
                    } else {
                        System.out.println("Not Valid Card.");
                        continue;
                    }
                }
                System.out.println("Invalid input.");
            }

        }

    }

    /**
     * This method is for getting the round
     * status so far.
     *
     */
    private void printPlayers(){
        ArrayList<Player> temp = board.getPlayers();

        for(int i = 0; i < temp.size(); i++){
            System.out.print("Player " + (i + 1) + " Cards : " + temp.get(i).getNumberOfCard());
            if(i != temp.size() - 1)
                System.out.print(" | ");
        }

        System.out.print("\n");
    }

    /**
     * This method is for showing this round result.
     *
     */
    private void printThisRound(){

        System.out.println("\nRound Information.\n");
        results.get(results.size() - 1).print();

        System.out.println("And The Round Winner Is Player" + (results.get(results.size() - 1).getRoundWinner() + 1));
    }

    /**
     * This method is for showing the all rounds
     * results.
     *
     */
    private void printAllResult(){

        System.out.println("\nRounds Information.\n");
        System.out.print("------- | ");

        for(int j = 0; j < numberOfPlayers; j++){
            System.out.print(" ------------  ");
        }

        System.out.print("\n");
        for(int i = 0; i < results.size(); i++){
            System.out.print("Round " + (i + 1) + " :");
            System.out.print("  ");
            results.get(i).print();
        }
        System.out.print("\n");
    }
}
