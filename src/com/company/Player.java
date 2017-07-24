package com.company;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Player {
    // list of cards in their personal deck
    private Queue<Card> personalDeck = new LinkedList<Card>();

    private String name;

    public Player(String name) {
        this.name = name;
    }

    public Card drawTop() {
        return personalDeck.remove();
    }

    public void addCard(Card newCard) {
        personalDeck.add(newCard);
    }

    public void addCard(List<Card> newCardList) {
        for(Card card : newCardList) {
            addCard(card);
        }
    }

    public int getDeckSize() {
        return personalDeck.size();
    }

    public void printDeck() {
        StringBuilder out = new StringBuilder();
        for(Card card : personalDeck) {
            out.append(card.toString());
        }
    }

    public String toString() {

        return("Player: " + name + ", deck: " + getDeckSize());
    }
}
