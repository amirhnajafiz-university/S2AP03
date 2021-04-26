package com.myPack;

import java.util.ArrayList;

/**
 * This class is for printing the output
 * inside the cmd page.
 *
 */
public class PrintTheBoard {

    // The list of cards.
    private ArrayList<Card> cards;

    // This Is For CMD output
    private int graphicType;

    /**
     * The main constructor of the program to give
     * the class the list of cards.
     *
     * @param cards the list of cards
     */
    public PrintTheBoard(ArrayList<Card> cards, int graphicType){
        this.cards = cards;
        this.graphicType = graphicType;
        print(1);
    }

    /**
     * This is and overload of the constructor
     * if we wanted to show a single card.
     *
     * @param middleCard the card we want to show
     */
    public PrintTheBoard(Card middleCard, int graphicType){
        cards = new ArrayList<>();
        cards.add(middleCard);
        this.graphicType = graphicType;
        print(0);
    }

    /**
     * The only method of this class to print the output.
     *
     */
    private void print(int middleCard){
        for(int i = 0; i < cards.size(); i++){
            if(graphicType == 1)
                System.out.print("|$$$$$$$| ");
            else {
                switch(cards.get(i).getColor()){
                    case 1:
                        System.out.print("\u001b[31m" + "|$$$$$$$|" + "\u001b[0m" + " ");
                        break;
                    case 2:
                        System.out.print("\u001b[34m" + "|$$$$$$$|" + "\u001b[0m" + " ");
                        break;
                    case 3:
                        System.out.print("\u001b[32m" + "|$$$$$$$|" + "\u001b[0m" + " ");
                        break;
                    case 4:
                        System.out.print("\u001b[33m" + "|$$$$$$$|" + "\u001b[0m" + " ");
                        break;
                    default:
                        System.out.print("|$$$$$$$| ");
                }
            }
        }
        System.out.print("\n");
        for(int i = 0; i < cards.size(); i++){
            if(cards.get(i) instanceof NormalCard || cards.get(i) instanceof BonusCard){
                Card temp = (Card) cards.get(i);
                if(graphicType == 1) {
                    String color = null;
                    switch (temp.getColor()) {
                        case 1:
                            color = "RED";
                            break;
                        case 2:
                            color = "BLU";
                            break;
                        case 3:
                            color = "GRN";
                            break;
                        case 4:
                            color = "YLW";
                            break;
                    }
                    System.out.print("|  " + color + "  | ");
                } else {
                    switch (temp.getColor()) {
                        case 1:
                            System.out.print("\u001b[31m" + "|       |" + "\u001b[0m" + " ");
                            break;
                        case 2:
                            System.out.print("\u001b[34m" + "|       |" + "\u001b[0m" + " ");
                            break;
                        case 3:
                            System.out.print("\u001b[32m" + "|       |" + "\u001b[0m" + " ");
                            break;
                        case 4:
                            System.out.print("\u001b[33m" + "|       |" + "\u001b[0m" + " ");
                            break;
                    }
                }
            } else {
                WildCard temp = (WildCard) cards.get(i);
                if(temp.getTypeOfWild().equals("wildDraw"))
                    System.out.print("| Wild  | ");
                else
                    System.out.print("| Color | ");
            }
        }
        System.out.print("\n");
        for(int i = 0; i < cards.size(); i++){
            if(cards.get(i) instanceof NormalCard){
                NormalCard temp = (NormalCard) cards.get(i);
                if(graphicType == 1)
                    System.out.print("|   " + temp.getCardNumber() + "   | ");
                else {
                    switch (temp.getColor()) {
                        case 1:
                            System.out.print("\u001b[31m" + "|   " + temp.getCardNumber() + "   |" + "\u001b[0m" + " ");
                            break;
                        case 2:
                            System.out.print("\u001b[34m" + "|   " + temp.getCardNumber() + "   |" + "\u001b[0m" + " ");
                            break;
                        case 3:
                            System.out.print("\u001b[32m" + "|   " + temp.getCardNumber() + "   |" + "\u001b[0m" + " ");
                            break;
                        case 4:
                            System.out.print("\u001b[33m" + "|   " + temp.getCardNumber() + "   |" + "\u001b[0m" + " ");
                            break;
                    }
                }
            } else if(cards.get(i) instanceof WildCard){
                WildCard temp = (WildCard) cards.get(i);
                if(temp.getTypeOfWild().equals("wildDraw"))
                    System.out.print("| Draw  | ");
                else
                    System.out.print("|       | ");
            } else {
                BonusCard temp = (BonusCard) cards.get(i);
                if(graphicType == 1) {
                    if (temp.getBonusType().equals("skip"))
                        System.out.print("| Skip  | ");
                    else if (temp.getBonusType().equals("reverse"))
                        System.out.print("|Reverse| ");
                    else
                        System.out.print("| Draw  | ");
                } else {
                    if (temp.getBonusType().equals("skip")){
                        switch (temp.getColor()) {
                            case 1:
                                System.out.print("\u001b[31m" + "| Skip  | " + "\u001b[0m");
                                break;
                            case 2:
                                System.out.print("\u001b[34m" + "| Skip  | " + "\u001b[0m");
                                break;
                            case 3:
                                System.out.print("\u001b[32m" + "| Skip  | " + "\u001b[0m");
                                break;
                            case 4:
                                System.out.print("\u001b[33m" + "| Skip  | " + "\u001b[0m");
                                break;
                        }
                    } else if (temp.getBonusType().equals("reverse")){
                        switch (temp.getColor()) {
                            case 1:
                                System.out.print("\u001b[31m" + "|Reverse| " + "\u001b[0m");
                                break;
                            case 2:
                                System.out.print("\u001b[34m" + "|Reverse| " + "\u001b[0m");
                                break;
                            case 3:
                                System.out.print("\u001b[32m" + "|Reverse| " + "\u001b[0m");
                                break;
                            case 4:
                                System.out.print("\u001b[33m" + "|Reverse| " + "\u001b[0m");
                                break;
                        }
                    } else {
                        switch (temp.getColor()) {
                            case 1:
                                System.out.print("\u001b[31m" + "| Draw  | " + "\u001b[0m");
                                break;
                            case 2:
                                System.out.print("\u001b[34m" + "| Draw  | " + "\u001b[0m");
                                break;
                            case 3:
                                System.out.print("\u001b[32m" + "| Draw  | " + "\u001b[0m");
                                break;
                            case 4:
                                System.out.print("\u001b[33m" + "| Draw  | " + "\u001b[0m");
                                break;
                        }
                    }
                }
            }
        }
        System.out.print("\n");
        for(int i = 0; i < cards.size(); i++){
            if(graphicType == 1)
                System.out.print("|$$$$$$$| ");
            else {
                switch(cards.get(i).getColor()){
                    case 1:
                        System.out.print("\u001b[31m" + "|$$$$$$$|" + "\u001b[0m" + " ");
                        break;
                    case 2:
                        System.out.print("\u001b[34m" + "|$$$$$$$|" + "\u001b[0m" + " ");
                        break;
                    case 3:
                        System.out.print("\u001b[32m" + "|$$$$$$$|" + "\u001b[0m" + " ");
                        break;
                    case 4:
                        System.out.print("\u001b[33m" + "|$$$$$$$|" + "\u001b[0m" + " ");
                        break;
                    default:
                        System.out.print("|$$$$$$$| ");
                }
            }
        }
        System.out.print("\n");
        if(middleCard == 1) {
            for (int i = 0; i < cards.size(); i++) {
                if(i < 10)
                    System.out.print("   [" + (i + 1) + "]    ");
                else
                    System.out.print("   [" + (i + 1) + "]   ");
            }
            System.out.print("\n");
        }
    }
}
