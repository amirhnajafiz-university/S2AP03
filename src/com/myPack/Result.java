package com.myPack;

import java.util.ArrayList;

/**
 * This class is for a round status to see who one the
 * round and see the scores of other.
 */
public class Result {

    // The array of players
    private Player[] holdPlayers;

    // This round winner
    private int roundWinner;

    /**
     * The main constructor of the program.
     *
     * @param players the list of players
     */
    public Result(ArrayList<Player> players){
        holdPlayers = new Player[players.size()];
        for(int i = 0; i < players.size(); i++)
            holdPlayers[i] = players.get(i);
        sort();
    }

    /**
     * This is a method to sort the list
     * of the players based on their score from low to high
     * and save the round winner.
     *
     */
    private void sort(){

        for (int j = 1; j < holdPlayers.length; j++) {

            int key = holdPlayers[j].getScore();
            int i = j - 1;
            while ((i >= 0) && (holdPlayers[i].getScore() > key)) {
                Player temp = holdPlayers[i + 1];
                holdPlayers[i + 1] = holdPlayers[i];
                holdPlayers[i] = temp;
                i--;
            }
        }

        roundWinner = holdPlayers[0].getPlayerNumber();
    }

    /**
     * A getter method for getting the round winner.
     *
     * @return the winner of this round
     */
    public int getRoundWinner() {
        return roundWinner;
    }

    /**
     * A method for printing this round information.
     *
     */
    public void print(){

        for(int i = 0; i < holdPlayers.length; i++){
            System.out.print("Player " + (holdPlayers[i].getPlayerNumber() + 1) + " : " + holdPlayers[i].getScore());
            if(i != holdPlayers.length - 1)
                System.out.print(" | ");
        }
        System.out.print("\n");
    }
}
