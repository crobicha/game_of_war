package com.company;

import org.w3c.dom.ranges.RangeException;

//enum CardSuit {
//    DIAMOND(0), CLUB, SPADE, HEART;
//}

class Card {
    private int suit;
    private int value;

    Card(int value, int suit) {
        this.value = value;
        this.suit = suit;

        if(value < 2 || value > 14) {
            throw new RuntimeException("Invalid value " + value);
        }
    }

    public int getValue() {
        return value;
    }

    // 2->10 use literal values
    // J -> 11, Q -> 12, K -> 13, A-> 14
    public String toString() {
        StringBuilder out = new StringBuilder();

        if(value <= 9) {
            out.append(value);
        }
        else if(value == 10) {
            out.append("T");
        }
        else if(value == 11) {
            out.append("J");
        }
        else if(value == 12) {
            out.append("Q");
        }
        else if(value == 13) {
            out.append("K");
        }
        else if(value == 14) {
            out.append("A");
        }

        if(suit == 0) {
            out.append("C");
        }
        else if(suit == 1) {
            out.append("S");
        }
        else if(suit == 2) {
            out.append("D");
        }
        else if(suit == 3) {
            out.append("H");
        }

        return out.toString();
    }
}
