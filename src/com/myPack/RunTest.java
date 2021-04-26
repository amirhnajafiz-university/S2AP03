package com.myPack;

import java.util.Scanner;

/**
 * This is a test for the program.
 *
 */
public class RunTest {

    public static void main(String[] args) {

        // Creating an instance of scanner
        Scanner scanner = new Scanner(System.in);

        int numberOfPlayers;
        int gameMode;
        int graphicType;

        System.out.print("If You Are Running In CMD Enter 1 > ");
        graphicType = scanner.nextInt();

        System.out.print("\nEnter 1 for Single Mode Or 2 For Multi Player Mode > ");
        gameMode = scanner.nextInt();

        if(gameMode == 1)
            System.out.print("Enter The Number Of Players ( 3 - 4 - 5 ) > ");
        else if(gameMode == 2)
            System.out.print("Enter The Number Of Players > ");
        else {
            System.out.print("\nInvalid Input\n");
            return;
        }

        numberOfPlayers = scanner.nextInt();
        Uno uno = new Uno(numberOfPlayers, gameMode, graphicType);
        uno.startTheGame();
    }
}
